// CommentController.java

package com.socialmediaapp.twitter.controller;

import com.socialmediaapp.twitter.commentdto.CommentEditDTO;
import com.socialmediaapp.twitter.commentdto.CommentRequestDTO;
import com.socialmediaapp.twitter.commentdto.CommentResponseDTO;
import com.socialmediaapp.twitter.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    public ResponseEntity<String> createComment(@RequestBody CommentRequestDTO requestDTO) {
        String response = commentService.createNewComment(requestDTO);
        if (response.equals("Comment created successfully")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else if (response.equals("User does not exist") || response.equals("Post does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @GetMapping("/comment/{commentID}")
    public ResponseEntity<?> getComment(@PathVariable int commentID) {
        CommentResponseDTO comment = commentService.getComment(commentID);
        if (comment != null) {
            return ResponseEntity.ok(comment);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment does not exist");
        }
    }

    @PatchMapping("/comment")
    public ResponseEntity<String> editComment(@RequestBody CommentEditDTO editDTO) {
        String response = commentService.editComment(editDTO);
        if (response.equals("Comment edited successfully")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/comment/{commentID}")
    public ResponseEntity<String> deleteComment(@PathVariable int commentID) {
        String response = commentService.deleteComment(commentID);
        if (response.equals("Comment deleted")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
