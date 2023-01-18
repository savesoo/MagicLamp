package com.app.magiclamp.repository;

import com.app.magiclamp.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    // 각 별점을 매긴 사람 수
    long countByIsbn_IsbnAndStar(String isbn, Integer star);

    // 총 별점을 매긴 사람 수
    long countByIsbn_Isbn(String isbn);

    // 본인이 쓴 리뷰 정보
    Optional<Review> findByIsbn_IsbnAndReviewer_Userindex(String isbn, Integer userindex);

    // 삭제
    @Transactional
    @Modifying
    @Query("delete from Review r where r.reviewindex = ?1")
    int deleteByReviewindex(Integer reviewindex);

}
