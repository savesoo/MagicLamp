package com.app.magiclamp.model;

import com.app.magiclamp.entity.Book;
import com.app.magiclamp.entity.Review;
import com.app.magiclamp.entity.User;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ReviewDTO {
    private int reviewindex;
    private String isbn;
    private Integer reviewer;
    private Integer star;
    private String reviewcontent;

    private String regdate;

    private String username;

    public Review toReviewEntity(){
        return Review.builder()
                .reviewindex(reviewindex)
                .isbn(Book.builder().isbn(isbn).build())
                .reviewer(User.builder().userindex(reviewer).build())
                .star(star)
                .reviewcontent(reviewcontent)
                .build();
    }
}
