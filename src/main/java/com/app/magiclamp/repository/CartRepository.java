package com.app.magiclamp.repository;

import com.app.magiclamp.entity.Book;
import com.app.magiclamp.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    // 장바구니 책 수량 변경
    @Transactional
    @Modifying
    @Query("update Cart c set c.bookcount = ?1 where c.cartindex = ?2 and c.userindex = ?3")
    int updateBookcountByCartindex(int bookcount, int cartindex, int userindex);

    // 장바구니 삭제
    @Transactional
    @Modifying
    @Query("delete from Cart c where c.cartindex = ?1 and c.userindex = ?2")
    int deleteByCartindex(int cartindex, int userindex);

    // 구매 후 장바구니에서 삭제
    @Transactional
    @Modifying
    @Query("delete from Cart c where c.userindex = :userindex and c.isbn = :isbn")
    int deleteByUserindexAndIsbn(Integer userindex, Book isbn);

}
