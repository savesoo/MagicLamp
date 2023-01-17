package com.app.magiclamp.service.review;

import com.app.magiclamp.model.ReviewDTO;
import com.app.magiclamp.entity.Review;
import com.app.magiclamp.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReviewInsertService {
    @Autowired
    private ReviewRepository reviewRepository;

    public Review insertReview(ReviewDTO reviewDTO){
        Review review = reviewDTO.toReviewEntity();
        return reviewRepository.save(review);
    }
}
