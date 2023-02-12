package com.app.magiclamp.service.review;

import com.app.magiclamp.entity.Review;
import com.app.magiclamp.model.review.ReviewDTO;
import com.app.magiclamp.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewModifyService {
    @Autowired
    private ReviewRepository reviewRepository;

    public int modifyReview(ReviewDTO reviewDTO){
        Review review = reviewDTO.toReviewEntity();
        review.setRegdate(reviewDTO.getRegdate());
        return reviewRepository.save(review) != null ? 1 : 0;
    }
}
