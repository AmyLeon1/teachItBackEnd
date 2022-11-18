package com.teachit.teachItBackEnd.controller;


import com.teachit.teachItBackEnd.model.Todo;
import com.teachit.teachItBackEnd.repository.TodoRepo;
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
    private TodoRepo todoRepo;

    @GetMapping("/users/{email}/todos")
    public List<Todo> getAllTodos(@PathVariable String email){
        return todoRepo.findByEmail(email);
        //return todoService.findAll();
    }


    @GetMapping("/users/{email}/todos/{id}")
    public Todo getTodo(@PathVariable String email, @PathVariable long id){
        return todoRepo.findById(id).get();
        //return todoService.findById(id);
    }

    //DELETE /users/{username}/todos/{id}
    @DeleteMapping("/users/{email}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(
            @PathVariable String email, @PathVariable long id){

        //Todo todo = todoService.deleteById(id);
        todoRepo.deleteById(id);

        return ResponseEntity.noContent().build();
        //return ResponseEntity.notFound().build();
    }

    //Edit/Update a Todo
    //PUT /users/{user_name}/todos/{todo_id}
    @PutMapping("/users/{email}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(
            @PathVariable String email,
            @PathVariable long id, @RequestBody Todo todo){

        //Todo todoUpdated = todoService.save(todo);
        Todo todoUpdated = todoRepo.save(todo);

        return new ResponseEntity<Todo>(todo, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/users/{email}/todos")
    public ResponseEntity<Void> createTodo(
            @PathVariable String email, @RequestBody Todo todo
    ){
        todo.setEmail(email);
        Todo createdTodo = todoRepo.save(todo);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdTodo.getId()).toUri();

        return ResponseEntity.created(uri).build();


    }



}
