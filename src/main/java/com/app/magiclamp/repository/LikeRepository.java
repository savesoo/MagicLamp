package com.app.magiclamp.repository;

import com.app.magiclamp.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface LikeRepository extends JpaRepository<Like, Integer> {
    Optional<Like> findByReviewindex_ReviewindexAndUserindex_Userindex(Integer reviewindex, Integer userindex);

}
