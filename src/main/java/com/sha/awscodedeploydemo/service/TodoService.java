package com.sha.awscodedeploydemo.service;

import com.sha.awscodedeploydemo.model.Images;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TodoService {
    Images saveTodo(String username, String title, String description, MultipartFile[] file) throws IOException;

    byte[] downloadTodoImage(String id);

    List<Images> getAllTodosByUser(String username);
}