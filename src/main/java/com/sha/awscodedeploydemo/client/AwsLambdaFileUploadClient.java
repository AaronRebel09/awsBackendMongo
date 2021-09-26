package com.sha.awscodedeploydemo.client;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import java.util.Map;

public interface AwsLambdaFileUploadClient {
    @Headers({
            "Content-Type:application/json",
            "Accept: application/json"
    })
    @POST("prod/subida")
    Call<Map<String, Object>> sendImagesToAwsLambda(@Body Map<String, Object> request);
}
