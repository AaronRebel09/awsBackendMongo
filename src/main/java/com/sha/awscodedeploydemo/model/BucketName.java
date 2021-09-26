package com.sha.awscodedeploydemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BucketName {
    BUCKET_IMAGE("spring-frontend-test");
    private final String bucketName;
}