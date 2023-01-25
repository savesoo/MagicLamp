package com.app.magiclamp.repository;

import com.app.magiclamp.entity.Like;
import com.app.magiclamp.entity.Review;
import com.app.magiclamp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface LikeRepository extends JpaRepository<Like, Integer> {
    Optional<Like> findByReviewindex_ReviewindexAndUserindex_Userindex(Integer reviewindex, Integer userindex);

    @Transactional
    @Modifying
    @Query("delete from Like l where l.reviewindex = ?1 and l.userindex = ?2")
    int deleteByReviewindexAndUserindex(Review reviewindex, User userindex);
}
