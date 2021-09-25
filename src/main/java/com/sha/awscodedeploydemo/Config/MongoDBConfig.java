/*
package com.sha.awscodedeploydemo.Config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.sha.awscodedeploydemo")
public class MongoDBConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Value("${mongodb.host:localhost}")
    private String dbUri;
    @Value("${mongodb.port:27017}")
    private int dbPort;
    @Value("${mongodb.database.name:YOUR_DOCUMENTDB_NAME}")
    private String dbName;
    @Value("${mongodb.username:}")
    private String dbUser;
    @Value("${mongodb.password:}")
    private String dbPassword;

    @Override
    public String getDatabaseName() {
        return dbName;
    }

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(getConnectionString());
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings);
    }

    private String getConnectionString() {
        if (activeProfile.contains("local")) {
            return String.format("mongodb://%s:%s/%s", dbUri, dbPort, dbName);
        }
        return String.format("mongodb://%s:%s@%s:%s/%s?ssl=true&replicaSet=rs0&readpreference=secondaryPreferred&retrywrites=false",
                dbUser, dbPassword, dbUri, dbPort, dbName);
    }
}
*/
