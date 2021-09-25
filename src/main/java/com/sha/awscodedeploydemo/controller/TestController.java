package com.sha.awscodedeploydemo.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import com.sha.awscodedeploydemo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
  
  @Autowired
  private RoleRepository repo;
  
  @GetMapping("/all")
  public String allAccess() {
      return "Public Content.";
  }
  
  @GetMapping("/user")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public String userAccess() {
      return "User Content.";
  }

  @GetMapping("/mod")
  @PreAuthorize("hasRole('MODERATOR')")
  public String moderatorAccess() {
      return "Moderator Board.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
      return "Admin Board.";
  }
  
  @GetMapping("/roles")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public Map<String, Object> listRoles(){
    Map<String, Object> map = new LinkedHashMap<>();
    map.put("roles", repo.findAll());
    return map;
  }
  
}
