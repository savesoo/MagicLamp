package com.app.magiclamp.repository;

import com.app.magiclamp.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    // 장바구니 책 수량 변경
    @Transactional
    @Modifying
    @Query("update Cart c set c.bookcount = ?1 where c.cartindex = ?2")
    int updateBookcountByCartindex(int bookcount, int cartindex);

    // 장바구니 삭제
    @Transactional
    @Modifying
    @Query("delete from Cart c where c.cartindex = ?1")
    int deleteByCartindex(int cartindex);

    // 장바구니 리스트
    @Query("select c from Cart c where c.userindex = ?1 order by c.cartindex DESC")
    List<Cart> findByUserindexOrderByCartindexDesc(Integer userindex);







}
