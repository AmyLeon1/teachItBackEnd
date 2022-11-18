//package com.teachit.teachItBackEnd.controller;
//
//import com.teachit.teachItBackEnd.dto.PostDto;
//import com.teachit.teachItBackEnd.model.Post;
//import com.teachit.teachItBackEnd.repository.PostRepository;
//import com.teachit.teachItBackEnd.repository.RegistrationRepo;
//import com.teachit.teachItBackEnd.service.PostService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/posts/")
//public class PostController {
//
//    //inject repos
//    @Autowired
//    private RegistrationRepo registrationRepo;
//   @Autowired
//    private PostRepository postRepo;
//
//
//
//
//
//
//
//    @Autowired
//    private PostService postService;
//
//    @PostMapping
//    public ResponseEntity createPost(@RequestBody PostDto postDto) {
//        postService.createPost(postDto);
//        return new ResponseEntity(HttpStatus.OK);
//    }
//
//
//
//
//    @PostMapping(path= "/postPost")
//    public Post addPost(@RequestBody Post post) throws Exception {
//
//
//        Post postObj = null;
//        //pass in user to the registration service save method
//        postObj = postService.savePost(post);
//        return postObj;
//    }
//
////    @GetMapping("/all")
////    public ResponseEntity<List<PostDto>> showAllPosts() {
////        return new ResponseEntity<>(postService.showAllPosts(), HttpStatus.OK);
////    }
////
////    @GetMapping("/get/{id}")
////    public ResponseEntity<PostDto> getSinglePost(@PathVariable @RequestBody Long id) {
////        return new ResponseEntity<>(postService.readSinglePost(id), HttpStatus.OK);
////    }
//}