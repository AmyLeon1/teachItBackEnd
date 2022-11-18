package com.teachit.teachItBackEnd.controller;



import com.teachit.teachItBackEnd.model.PostTest;
import com.teachit.teachItBackEnd.model.Todo;
import com.teachit.teachItBackEnd.repository.PostTestRepository;
import com.teachit.teachItBackEnd.repository.TodoRepo;
import com.teachit.teachItBackEnd.service.PostTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PostTestController {


    @Autowired
    private PostTestService postTestService;


    @PostMapping(path= "/addPost")
    public PostTest addPost(@RequestBody PostTest post) throws Exception {


        PostTest postObj = null;

        //pass in user to the registration service save method
         postObj  = postTestService.saveNewPost(post);
        return postObj;
    }





    @Autowired
    private PostTestRepository postTestRepositoryRepo;

    @GetMapping("/users/{email}/posts")
    public List<PostTest> getAllPosts(@PathVariable String email){
        return postTestRepositoryRepo.findByEmail(email);
        //return todoService.findAll();
    }


    @GetMapping("/users/{email}/posts/{id}")
    public PostTest getPost(@PathVariable String email, @PathVariable long id){
        return postTestRepositoryRepo.findById(id).get();
        //return todoService.findById(id);
    }

    //DELETE /users/{username}/todos/{id}
    @DeleteMapping("/users/{email}/posts/{id}")
    public ResponseEntity<Void> deletePost(
            @PathVariable String email, @PathVariable long id){

        //Todo todo = todoService.deleteById(id);
        postTestRepositoryRepo.deleteById(id);

        return ResponseEntity.noContent().build();
        //return ResponseEntity.notFound().build();
    }

    //Edit/Update a Todo
    //PUT /users/{user_name}/todos/{todo_id}
    @PutMapping("/users/{email}/posts/{id}")
    public ResponseEntity<PostTest> updatePostTest(
            @PathVariable String email,
            @PathVariable long id, @RequestBody PostTest postTest){

        //Todo todoUpdated = todoService.save(todo);
        PostTest postTestupdated = postTestRepositoryRepo.save(postTest);

        return new ResponseEntity<PostTest>(postTest, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/users/{email}/posts")
    public ResponseEntity<Void> createPost(
            @PathVariable String email, @RequestBody PostTest postTest
    ){
        postTest.setEmail(email);
        PostTest createdPostTest = postTestRepositoryRepo.save(postTest);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdPostTest.getId()).toUri();

        return ResponseEntity.created(uri).build();


    }


}
