package com.sha.awscodedeploydemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ApiController
{
    @GetMapping("/api/health")
    public ResponseEntity<?> healthCheck()
    {
        return ResponseEntity.ok("It works successfully.");
    }
}
