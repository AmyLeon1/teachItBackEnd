package com.teachit.teachItBackEnd.controller;

import com.teachit.teachItBackEnd.model.Todo;
import com.teachit.teachItBackEnd.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TodoController {

    @Autowired
    private TodoService service;

    @GetMapping("/users/{email}/todos")
    public List<Todo> getAllTodos(@PathVariable String email) {
        return service.findByEmail(email);
    }

    @GetMapping("/users/{email}/todos/{id}")
    public Todo getTodo(@PathVariable String email, @PathVariable long id) {
        return service.findById(id);
    }

    @DeleteMapping("/users/{email}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(
            @PathVariable String email, @PathVariable long id) {

        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{email}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(
            @PathVariable String email,
            @PathVariable long id, @RequestBody Todo todo) {

        Todo todoUpdated = service.save(todo);

        return new ResponseEntity<Todo>(todo, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/users/{email}/todos")
    public ResponseEntity<Void> createTodo(
            @PathVariable String email, @RequestBody Todo todo
    ) {
        todo.setEmail(email);
        Todo createdTodo = service.save(todo);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdTodo.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
