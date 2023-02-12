package com.app.magiclamp.service.review;

import com.app.magiclamp.mapper.ReviewMapper;
import com.app.magiclamp.model.review.ReviewDTO;
import com.app.magiclamp.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewListService {
    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private ReviewRepository reviewRepository;

    public List<ReviewDTO> getList(String isbn, int pageNum){
        int index = (pageNum-1) * 5;
        int count = 5;
        return reviewMapper.getList(isbn, index, count);
    }

    public List<ReviewDTO> findAllByLike(String isbn, int pageNum){
        int index = (pageNum-1) * 5;
        int count = 5;
        return reviewMapper.findAllByLike(isbn, index, count);
    }

    public List<ReviewDTO> findAllByHighRating(String isbn, int pageNum){
        int index = (pageNum-1) * 5;
        int count = 5;
        return reviewMapper.findAllByHighRating(isbn, index, count);
    }

    public long countPeople(String isbn){
        return reviewRepository.countByIsbn_Isbn(isbn);
    }
}
