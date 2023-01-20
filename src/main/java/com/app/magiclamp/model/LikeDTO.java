package com.app.magiclamp.model;

import com.app.magiclamp.entity.Like;
import com.app.magiclamp.entity.Review;
import com.app.magiclamp.entity.User;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class LikeDTO {
    private int likeindex;
    private Integer reviewindex;
    private Integer userindex;

    public Like toLikeEntity(){
        return Like.builder()
                .likeindex(likeindex)
                .reviewindex(Review.builder().reviewindex(reviewindex).build())
                .userindex(User.builder().userindex(userindex).build())
                .build();
    }
}
