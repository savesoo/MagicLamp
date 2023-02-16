package com.app.magiclamp.repository;

import com.app.magiclamp.entity.Order;
import com.app.magiclamp.model.order.OrderBookPageDTO;
import com.app.magiclamp.model.order.RequestOrderBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface OrderRepository extends JpaRepository<Order, Integer> {

    // isbn
    @Query("select o from Order o where o.isbn = :isbn")
    RequestOrderBook findByIsbn(String isbn);

    @Query("select o.usemileage from Order o where o.orderindex = :orderindex")
    int findMilByOrderindex(Integer orderindex);




    
}
