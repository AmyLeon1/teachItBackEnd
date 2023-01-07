package com.teachit.teachItBackEnd.controller;

import com.teachit.teachItBackEnd.model.Blog;
import com.teachit.teachItBackEnd.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;


    // ** Endpoints for blog posts ** //

    /* Method to retrieve all blog posts made by a certain user*/
    @GetMapping("/users/{email}/blogs")
    public List<Blog> getAllBlogs(@PathVariable String email) {
        return blogService.getByEmail(email);
    }

    /* Method to retrieve a certain blog by passing in its id */
    @GetMapping("/blogs/{id}")
    public Blog getBlogById(@PathVariable long id) {
        return blogService.findById(id);
    }


    /* Method to delete blogs */
    @DeleteMapping("/users/{email}/blogs/{id}")
    public ResponseEntity<Void> deleteBlog(
            @PathVariable String email, @PathVariable long id) {

        //call on blogService to delete the specified blog by passing in the id
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }

    /* Method to update an existing blog */
    @PutMapping("/users/{email}/blogs/{id}")
    public ResponseEntity<Blog> updateBlog(
            @PathVariable String email,
            @PathVariable long id, @RequestBody Blog blog) {

        // pass the updated blog to blogService to be saved in the repo
        Blog blogUpdated = blogService.saveBlog(blog);

        return new ResponseEntity<Blog>(blog, HttpStatus.OK);
    }

    /* Method to create a new blog */
    @PostMapping("/users/{email}/blogs")
    public ResponseEntity<Void> createBlog(
            @PathVariable String email, @RequestBody Blog blog
    ) {
        //set email with the email passed in as path variable
        blog.setEmail(email);
        // pass the new blog to blogService to be saved in the repo
        Blog createdBlog = blogService.saveBlog(blog);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdBlog.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
