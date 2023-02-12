package com.app.magiclamp.controller.review;

import com.app.magiclamp.entity.Like;
import com.app.magiclamp.model.review.LikeDTO;
import com.app.magiclamp.service.review.LikeDeleteService;
import com.app.magiclamp.service.review.LikeService;
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
    private LikeService likeService;

    @Autowired
    private LikeDeleteService likeDeleteService;

    @PostMapping(value = "/{reviewindex}")
    public ResponseEntity<Like> likeReview(@RequestBody LikeDTO likeDTO) {
        Like like = likeInsertService.insertLike(likeDTO);
        return new ResponseEntity<>(like, HttpStatus.OK);
    }

    @GetMapping(value = "/{reviewindex}/{userindex}")
    public ResponseEntity<Integer> isLikedBy(@PathVariable("reviewindex") int reviewindex, @PathVariable("userindex") int userindex) {
        Like ifme = likeService.findById(reviewindex, userindex);
        int result = ifme == null ? -1 : 0;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{reviewindex}/{userindex}")
    public ResponseEntity<Integer> deleteLike(@PathVariable("reviewindex") int reviewindex, @PathVariable("userindex") int userindex) {
        return new ResponseEntity<>(likeDeleteService.deleteLike(reviewindex, userindex), HttpStatus.OK);
    }
}
