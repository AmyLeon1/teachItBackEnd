package com.teachit.teachItBackEnd.controller;

import com.teachit.teachItBackEnd.model.Blog;
import com.teachit.teachItBackEnd.model.Comment;
import com.teachit.teachItBackEnd.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CommentController {

    //inject the service class
    @Autowired
    private CommentService commentService;

    // ** Endpoints for comment feature of blog posts ** //

    /* Endpoint to save a new comment into the db */
    @PostMapping("/blogs/{blog}/comments")
    public ResponseEntity<Void> createComment(
            @PathVariable Blog blog, @RequestBody Comment comment){
        //set blog with path variable
        comment.setBlog(blog);
        //pass in the comment to the service to be saved to the db
        Comment createdComment = commentService.saveComment(comment);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdComment.getCommentId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    /* Endpoint to get all comments related to a particular blog */
    @GetMapping("/blogs/{blog}/comments")
    public List<Comment> getAllComments(@PathVariable Blog blog){
        //pass in blog to the commentService getByBlog method and return the results
        return commentService.getByBlog(blog);
    }

}
