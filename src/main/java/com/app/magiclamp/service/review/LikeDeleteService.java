package com.app.magiclamp.service.review;

import com.app.magiclamp.entity.Review;
import com.app.magiclamp.entity.User;
import com.app.magiclamp.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeDeleteService {

    @Autowired
    private LikeRepository likeRepository;

    public int deleteLike(int reviewindex, int userindex){
        return likeRepository.deleteByReviewindexAndUserindex(Review.builder().reviewindex(reviewindex).build(), User.builder().userindex(userindex).build());
    }
}
