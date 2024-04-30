package com.socialmediaapp.twitter.postdto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostDTO {
    private String postBody;
    private int userID;
}