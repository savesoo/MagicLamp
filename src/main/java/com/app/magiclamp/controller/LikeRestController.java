package com.app.magiclamp.controller;

import com.app.magiclamp.entity.Like;
import com.app.magiclamp.model.LikeDTO;
import com.app.magiclamp.service.review.LikeDistinguishService;
import com.app.magiclamp.service.review.LikeInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
public class LikeRestController {
    @Autowired
    private LikeInsertService likeInsertService;

    @Autowired
    private LikeDistinguishService likeDistinguishService;

    @PostMapping(value = "/{reviewindex}")
    public ResponseEntity<Like> likeReview(@RequestBody LikeDTO likeDTO){
        Like like = likeInsertService.insertLike(likeDTO);
        return new ResponseEntity<>(like, HttpStatus.OK);
    }

    @GetMapping(value = "/{reviewindex}/{userindex}")
    public ResponseEntity<Integer> ifMe(@PathVariable("reviewindex") int reviewindex, @PathVariable("userindex") int userindex){
        Like ifme = likeDistinguishService.distinguishMe(reviewindex, userindex);
        int result = ifme==null? -1 : 0;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
