package com.socialmediaapp.twitter.controller;

import com.socialmediaapp.twitter.postdto.EditPostDTO;
import com.socialmediaapp.twitter.postdto.PostDTO;
import com.socialmediaapp.twitter.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/post")
    public ResponseEntity<String> createPost(@RequestBody PostDTO requestDTO) {
        String response = postService.createNewPost(requestDTO);
        if (response.equals("User does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
    }

    @GetMapping("/post")
    public ResponseEntity<Object> getPost(@RequestParam("postID") Integer postID) {
        Object post = postService.getPost(postID);
        if (post == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post does not exist");
        } else {
            return ResponseEntity.ok(post);
        }
    }

    @PatchMapping("/post")
    public ResponseEntity<String> editPost(@RequestBody EditPostDTO editDTO) {
        String response = postService.editPost(editDTO);
        if (response.equals("Post edited successfully")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/post")
    public ResponseEntity<String> deletePost(@RequestParam Integer postID) {
        String response = postService.deletePost(postID);
        if (response.equals("Post does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            return ResponseEntity.ok(response);
        }
    }
}
