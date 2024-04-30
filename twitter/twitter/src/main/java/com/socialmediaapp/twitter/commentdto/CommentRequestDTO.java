package com.socialmediaapp.twitter.commentdto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentRequestDTO {
    private String commentBody;
    private int postID;
    private int userID;
}
