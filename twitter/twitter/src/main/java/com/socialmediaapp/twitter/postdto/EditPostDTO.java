package com.socialmediaapp.twitter.postdto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EditPostDTO {
    private int postID;
    private String postBody;
}
