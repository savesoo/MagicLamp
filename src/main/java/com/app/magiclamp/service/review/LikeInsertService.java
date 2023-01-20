package com.app.magiclamp.service.review;

import com.app.magiclamp.entity.Like;
import com.app.magiclamp.model.LikeDTO;
import com.app.magiclamp.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeInsertService {
    @Autowired
    private LikeRepository likeRepository;

    public Like insertLike(LikeDTO likeDTO){
        Like like = likeDTO.toLikeEntity();
        return likeRepository.save(like);
    }
}
