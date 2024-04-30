// CommentService.java

package com.socialmediaapp.twitter.service;

import com.socialmediaapp.twitter.commentdto.CommentEditDTO;
import com.socialmediaapp.twitter.commentdto.CommentResponseDTO;
import com.socialmediaapp.twitter.comments.Comment;
import com.socialmediaapp.twitter.dao.CommentRepo;
import com.socialmediaapp.twitter.dao.PostRepo;
import com.socialmediaapp.twitter.dao.UserRepo;
import com.socialmediaapp.twitter.commentdto.CommentRequestDTO;
import com.socialmediaapp.twitter.post.Post;
import com.socialmediaapp.twitter.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    public String createNewComment(CommentRequestDTO requestDTO) {
        // Check if the user exists
        Optional<User> optionalUser = userRepo.findById(requestDTO.getUserID());
        if (!optionalUser.isPresent()) {
            return "User does not exist";
        }

        // Check if the post exists
        Optional<Post> optionalPost = postRepo.findById(requestDTO.getPostID());
        if (!optionalPost.isPresent()) {
            return "Post does not exist";
        }

        // Create the comment entity
        Comment comment = new Comment();
        comment.setCommentBody(requestDTO.getCommentBody());
        comment.setDate(new Date());
        comment.setPost(optionalPost.get());
        comment.setUser(optionalUser.get());

        // Save the comment in the database
        commentRepo.save(comment);

        return "Comment created successfully";
    }

    public CommentResponseDTO getComment(int commentID) {
        Comment comment = commentRepo.findById(commentID).orElse(null);
        if (comment != null) {
            CommentResponseDTO responseDTO = new CommentResponseDTO();
            responseDTO.setCommentID(comment.getCommentID());
            responseDTO.setCommentBody(comment.getCommentBody());
            // Set comment creator details if available
            // responseDTO.setCommentCreator(...);
            return responseDTO;
        } else {
            return null;
        }
    }

    public String editComment(CommentEditDTO editDTO) {
        // Check if the comment exists
        Comment comment = commentRepo.findById(editDTO.getCommentID()).orElse(null);
        if (comment == null) {
            return "Comment does not exist";
        }

        // Update the comment body
        comment.setCommentBody(editDTO.getCommentBody());
        commentRepo.save(comment);

        return "Comment edited successfully";
    }

    public String deleteComment(int commentID) {
        Optional<Comment> optionalComment = commentRepo.findById(commentID);
        if (optionalComment.isPresent()) {
            commentRepo.deleteById(commentID);
            return "Comment deleted";
        } else {
            return "Comment does not exist";
        }
    }
}
