package com.teachit.teachItBackEnd.controller;

import com.teachit.teachItBackEnd.model.Blog;

import com.teachit.teachItBackEnd.repository.BlogRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
//@RestController
@RestController
public class BlogController {


    @Autowired
    private BlogRepo blogRepo;

    @GetMapping("/users/{email}/blogs")
    public List<Blog> getAllBlogs(@PathVariable String email){
        return blogRepo.findByEmail(email);
        //return todoService.findAll();
    }


    @GetMapping("/users/{email}/blogs/{id}")
    public Blog getBlog(@PathVariable String email, @PathVariable long id){
        return blogRepo.findById(id).get();
        //return todoService.findById(id);
    }

    //DELETE /users/{username}/todos/{id}
    @DeleteMapping("/users/{email}/blogs/{id}")
    public ResponseEntity<Void> deleteBlog(
            @PathVariable String email, @PathVariable long id){

        //Todo todo = todoService.deleteById(id);
        blogRepo.deleteById(id);

        return ResponseEntity.noContent().build();
        //return ResponseEntity.notFound().build();
    }


    @PutMapping("/users/{email}/blogs/{id}")
    public ResponseEntity<Blog> updateBlog(
            @PathVariable String email,
            @PathVariable long id, @RequestBody Blog blog){

        //Todo todoUpdated = todoService.save(todo);
        Blog blogUpdated = blogRepo.save(blog);

        return new ResponseEntity<Blog>(blog, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/users/{email}/blogs")
    public ResponseEntity<Void> createBlog(
            @PathVariable String email, @RequestBody Blog blog
    ){
        blog.setEmail(email);
        Blog createdBlog = blogRepo.save(blog);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdBlog.getId()).toUri();

        return ResponseEntity.created(uri).build();


    }
}
