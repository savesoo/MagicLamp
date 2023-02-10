package com.app.magiclamp.service.review;

import com.app.magiclamp.mapper.ReviewMapper;
import org.springframework.stereotype.Service;

@Service
public class UserOrderChkService {
    private final ReviewMapper reviewMapper;

    public UserOrderChkService(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    public long checkOrder(int userindex, String isbn){
        return reviewMapper.countByUserindexAndIsbn(isbn, userindex);
    }
}
