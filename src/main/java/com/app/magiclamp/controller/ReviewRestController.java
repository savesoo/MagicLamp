package com.app.magiclamp.controller;

import com.app.magiclamp.model.ReviewDTO;
import com.app.magiclamp.entity.Review;
import com.app.magiclamp.service.review.ReviewInsertService;
import com.app.magiclamp.service.review.ReviewListService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/review")
@Log4j2
public class ReviewRestController {

    @Autowired
    private ReviewInsertService reviewInsertService;

    @Autowired
    private ReviewListService reviewListService;


    @PostMapping
    public ResponseEntity<Review> insertReview(@RequestBody ReviewDTO reviewDTO){
        log.info("insert 전 : " + reviewDTO);

        Review resultReview = reviewInsertService.insertReview(reviewDTO);

        log.info("insert 후 : " + resultReview);
        return new ResponseEntity<>(resultReview, HttpStatus.OK);
    }

    @GetMapping(value = "/{isbn}/{pageNum}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getReviewList(@PathVariable("isbn") String isbn, @PathVariable("pageNum") int pageNum) {
        List<ReviewDTO> list = reviewListService.getList(isbn, pageNum);
        log.info(" list >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + list);
        long total = reviewListService.countPeople(isbn);
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("total", total);

        return map;
    }
}
