package com.app.magiclamp.repository;


import com.app.magiclamp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    
}
