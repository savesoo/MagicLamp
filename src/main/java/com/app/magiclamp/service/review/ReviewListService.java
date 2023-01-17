package com.app.magiclamp.service.review;

import com.app.magiclamp.mapper.ReviewMapper;
import com.app.magiclamp.model.ReviewDTO;
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
        int index = (pageNum-1) * 10;
        int count = 10;
        return reviewMapper.getList(isbn, index, count);
    }

    public long countPeople(String isbn){
        return reviewRepository.countByIsbn_Isbn(isbn);
    }
}
