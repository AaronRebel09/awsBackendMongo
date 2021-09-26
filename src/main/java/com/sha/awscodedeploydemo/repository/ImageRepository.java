package com.sha.awscodedeploydemo.repository;

import com.sha.awscodedeploydemo.model.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends MongoRepository<Image, String> {

}
