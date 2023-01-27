package com.app.magiclamp.service.review;

import com.app.magiclamp.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class UserOrderChkService {
    private final OrderRepository orderRepository;

    public UserOrderChkService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public long checkOrder(int userindex, String isbn){
        return orderRepository.countByUserindexAndIsbn(userindex, isbn);
    }
}
