package com.socialmediaapp.twitter.post;

import com.socialmediaapp.twitter.comments.Comment;
import com.socialmediaapp.twitter.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "POSTS")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postID;
    private String postBody;
    private Date date;
    private String email;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user; // This is where the user association is defined
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}