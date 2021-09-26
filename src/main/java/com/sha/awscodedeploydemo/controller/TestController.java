package com.sha.awscodedeploydemo.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.sha.awscodedeploydemo.model.Images;
import com.sha.awscodedeploydemo.repository.RoleRepository;
import com.sha.awscodedeploydemo.service.TodoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

  @Autowired
  TodoServiceImpl service;
  
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


  @PostMapping(
          path = "/todo",
          consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<Images> saveTodo(@RequestParam("title") String title,
                                         @RequestParam("description") String description,
                                         @RequestParam("user") String username,
                                         @RequestParam("file") MultipartFile[] files) {
    System.out.println("Files size: "+files.length+" subidos por: "+username);
    //return new ResponseEntity<>(HttpStatus.ACCEPTED);
    return new ResponseEntity<>(service.saveTodo(username, title, description, files), HttpStatus.OK);
  }

  @GetMapping("/getImagesByUser/{username}")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<List<Images>> getImages(@PathVariable(value="username") String username) {
    System.out.println("username: "+username);
    return new ResponseEntity<>(service.getAllTodosByUser(username), HttpStatus.OK);
  }

  @GetMapping(value = "/{id}/image/download", produces = MediaType.IMAGE_PNG_VALUE)
  public byte[] downloadTodoImage(@PathVariable("id") String id) {
    return service.downloadTodoImage(id);
  }
  
}
