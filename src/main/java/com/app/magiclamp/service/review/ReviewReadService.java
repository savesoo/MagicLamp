package com.app.magiclamp.service.review;

import com.app.magiclamp.entity.Review;
import com.app.magiclamp.mapper.ReviewMapper;
import com.app.magiclamp.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewReadService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewMapper reviewMapper;

    public Review readMyReview (String isbn, Integer reviewer){
        return reviewRepository.findByIsbn_IsbnAndReviewer_Userindex(isbn, reviewer).get();
    }

    public Double calculateTheAvg(String isbn){
        return reviewMapper.calculateTheAverageOfStar(isbn);
    }

    public long countPeople(String isbn){
        return reviewRepository.countByIsbn_Isbn(isbn);
    }

    public long count5(String isbn){
        return reviewRepository.countByIsbn_IsbnAndStar(isbn, 5);
    }

    public long count4(String isbn){
        return reviewRepository.countByIsbn_IsbnAndStar(isbn, 4);
    }

    public long count3(String isbn){
        return reviewRepository.countByIsbn_IsbnAndStar(isbn, 3);
    }

    public long count2(String isbn){
        return reviewRepository.countByIsbn_IsbnAndStar(isbn, 2);
    }

    public long count1(String isbn){
        return reviewRepository.countByIsbn_IsbnAndStar(isbn, 1);
    }
}
