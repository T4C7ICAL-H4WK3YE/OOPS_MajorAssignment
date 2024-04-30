package com.socialmediaapp.twitter.commentdto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentResponseDTO {
    private int commentID;
    private String commentBody;
    private CommentCreatorDTO commentCreator;
}
