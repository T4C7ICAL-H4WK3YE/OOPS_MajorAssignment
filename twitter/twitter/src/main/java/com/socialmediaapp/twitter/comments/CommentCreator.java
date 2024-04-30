package com.socialmediaapp.twitter.comments;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "COMMENT_CREATORS")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userID;
    private String name;
}
