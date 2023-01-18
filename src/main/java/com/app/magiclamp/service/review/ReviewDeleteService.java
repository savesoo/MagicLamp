package com.app.magiclamp.service.review;

import com.app.magiclamp.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewDeleteService {
    @Autowired
    private ReviewRepository reviewRepository;

    public int deleteByReviewIndex(int index){
        return reviewRepository.deleteByReviewindex(index);
    }
}
