package com.sha.awscodedeploydemo.config;

import com.sha.awscodedeploydemo.client.AwsLambdaFileUploadClient;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.net.ssl.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
public class RetrofitConfig {

    @Value("${aws.lambda.upload.url}")
    private String urlLambda;

    @Bean
    public AwsLambdaFileUploadClient getLambdaClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlLambda)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getUnsafeOkHttpClient())
                .build();
        return retrofit.create(AwsLambdaFileUploadClient.class);
    }

    public ConnectionSpec requireTls12(){
        return new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2)
                .build();
    }

    public HttpLoggingInterceptor interceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    /**
     *
     * Desactivar SSL de OkHttpClient
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    private OkHttpClient getUnsafeOkHttpClient() {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[] {};
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            } };

            final SSLContext sslContext;
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder
                    .followRedirects(true)
                    .followSslRedirects(true)
                    .connectionSpecs(Arrays.asList(requireTls12()))
                    .connectTimeout(600, TimeUnit.SECONDS)
                    .readTimeout(600, TimeUnit.SECONDS)
                    .writeTimeout(600, TimeUnit.SECONDS)
                    .addInterceptor(interceptor())
                    .cache(null)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
