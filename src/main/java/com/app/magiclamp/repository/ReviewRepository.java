package com.app.magiclamp.repository;

import com.app.magiclamp.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    // 책 별로 리뷰 리스트 (최신 등록순)
    List<Review> findByIsbn_IsbnOrderByRegdateDesc(String isbn);

    // 각 별점을 매긴 사람 수
    long countByIsbn_IsbnAndStar(String isbn, Integer star);

    // 총 별점을 매긴 사람 수
    long countByIsbn_Isbn(String isbn);

}
