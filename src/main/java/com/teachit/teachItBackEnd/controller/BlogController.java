package com.teachit.teachItBackEnd.controller;

import com.teachit.teachItBackEnd.model.Blog;

import com.teachit.teachItBackEnd.model.Comment;
import com.teachit.teachItBackEnd.repository.BlogRepo;

import com.teachit.teachItBackEnd.repository.CommentRepo;
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
    @Autowired
    private CommentRepo commentRepo;

    // ** Endpoints for comment feature of blog posts ** //

    //Passing in blog we want to save the comment to
    @PostMapping("/blogs/{blog}/comments")
    public ResponseEntity<Void> createComment(
             @PathVariable Blog blog, @RequestBody Comment comment){
        comment.setBlog(blog);
        Comment createdComment = commentRepo.save(comment);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdComment.getCommentId()).toUri();

        return ResponseEntity.created(uri).build();

    }
//
//    @GetMapping("/blogs/{blogId}/comments")
//    public List<Comment> getAllComments(@PathVariable long blogId){
//        return commentRepo.findByBlogId(blogId);
//        //return todoService.findAll();
//    }
//
//    @PutMapping("/blogs/{blogId}/comments/{id}")
//    public ResponseEntity<Comment> updateComment(
//            @PathVariable long blogId,
//            @PathVariable long id, @RequestBody Comment comment){
//
//        Comment commentUpdated = commentRepo.save(comment);
//
//        return new ResponseEntity<Comment>(comment, HttpStatus.OK);
//    }




    // ** Endpoints for blog posts ** //
    @GetMapping("/users/{email}/blogs")
    public List<Blog> getAllBlogs(@PathVariable String email){
        return blogRepo.findByEmail(email);
        //return todoService.findAll();
    }



    @GetMapping("/blogs/{id}")
    public Blog getBlogById( @PathVariable long id){
        return blogRepo.findById(id).get();
        //return todoService.findById(id);
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
