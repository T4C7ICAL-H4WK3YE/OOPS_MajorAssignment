package com.socialmediaapp.twitter.dao;

import com.socialmediaapp.twitter.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

    Post save(Post post);
}