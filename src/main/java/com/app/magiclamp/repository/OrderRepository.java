package com.app.magiclamp.repository;

import com.app.magiclamp.model.order.RequestOrderPage;
import com.app.magiclamp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Integer> {


    // isbn
    @Query("select o from Order o where o.isbn = :isbn")
    RequestOrderPage findByIsbn(String isbn);

    @Query("select o from Order o where o.userindex = :userindex")
    RequestOrderPage findByUserindex(Integer userindex);









}
