package com.socialmediaapp.twitter.comments;

import com.socialmediaapp.twitter.post.Post;
import com.socialmediaapp.twitter.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "COMMENTS")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentID;
    private String commentBody;
    private Date date;

    // Define the relationship with post
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    // Define the relationship with comment creator
    @ManyToOne
    @JoinColumn(name = "comment_creator_id", nullable = false)
    private CommentCreator commentCreator;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}