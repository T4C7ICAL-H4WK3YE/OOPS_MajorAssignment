package com.socialmediaapp.twitter.service;

import com.socialmediaapp.twitter.dao.PostRepo;
import com.socialmediaapp.twitter.dao.UserRepo;
import com.socialmediaapp.twitter.post.Post;
import com.socialmediaapp.twitter.postdto.EditPostDTO;
import com.socialmediaapp.twitter.postdto.PostDTO;
import com.socialmediaapp.twitter.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PostRepo postRepo;

    public String createNewPost(PostDTO requestDTO) {
        // Check if the user exists
        User user = userRepo.findById(requestDTO.getUserID()).orElse(null);
        if (user == null) {
            return "User does not exist";
        }

        // Create the post entity
        Post post = new Post();
        post.setPostBody(requestDTO.getPostBody());
        post.setDate(new Date());
        //post.setUser(user);

        // Save the post in the database
        postRepo.save(post);

        return "Post created successfully";
    }

    public Object getPost(Integer postID) {
        // Find the post by ID
        return postRepo.findById(postID).orElse(null);
    }

    public String editPost(EditPostDTO editDTO) {
        // Find the post by ID
        Post post = postRepo.findById(editDTO.getPostID()).orElse(null);
        if (post == null) {
            return "Post does not exist";
        }

        // Update the post body
        post.setPostBody(editDTO.getPostBody());
        postRepo.save(post);

        return "Post edited successfully";
    }

    public String deletePost(Integer postID) {
        Optional<Post> optionalPost = postRepo.findById(postID);
        if (optionalPost.isPresent()) {
            postRepo.deleteById(postID);
            return "Post deleted";
        } else {
            return "Post does not exist";
        }
    }
}