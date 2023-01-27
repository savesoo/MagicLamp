package com.app.magiclamp.repository;

import com.app.magiclamp.entity.Order;
import com.app.magiclamp.model.order.RequestOrderBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface OrderRepository extends JpaRepository<Order, Integer> {

    // isbn
    @Query("select o from Order o where o.isbn = :isbn")
    RequestOrderBook findByIsbn(String isbn);

    // 구매한 사람만 리뷰 등록할 수 있도록
    @Query("select count(o) from Order o where o.userindex = ?1 and o.isbn = ?2")
    long countByUserindexAndIsbn(Integer userindex, String isbn);
}
