package com.app.magiclamp.service.review;

import com.app.magiclamp.entity.Like;
import com.app.magiclamp.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;

    public Like findById(int reviewindex, int userindex){
        return likeRepository.findByReviewindex_ReviewindexAndUserindex_Userindex(reviewindex, userindex).orElse(null);
    }
}
