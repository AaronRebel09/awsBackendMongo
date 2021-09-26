package com.sha.awscodedeploydemo.repository;


import com.sha.awscodedeploydemo.model.Images;
import com.sha.awscodedeploydemo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TodoRepository extends MongoRepository<Images, String> {
    Images findByTitle(String title);
    List<Images> findByUser(User user);
}