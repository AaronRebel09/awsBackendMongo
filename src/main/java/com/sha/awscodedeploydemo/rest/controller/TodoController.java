package com.sha.awscodedeploydemo.rest.controller;


import com.sha.awscodedeploydemo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/todo")
@AllArgsConstructor
@CrossOrigin("*")
public class TodoController {
    TodoService service;

    /*@GetMapping
    public ResponseEntity<List<Images>> getTodos() {
        return new ResponseEntity<>(service.getAllTodos(), HttpStatus.OK);
    }*/

    /*@PostMapping(
            path = "",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Images> saveTodo(@RequestParam("title") String title,
                                           @RequestParam("description") String description,
                                           @RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(service.saveTodo(title, description, file), HttpStatus.OK);
    }*/

    @GetMapping(value = "{id}/image/download")
    public byte[] downloadTodoImage(@PathVariable("id") String id) {
        return service.downloadTodoImage(id);
    }
}
