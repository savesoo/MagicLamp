package com.app.magiclamp.service.mypage;

import com.app.magiclamp.mapper.MyPageReviewMapper;
import com.app.magiclamp.mapper.OrderHistoryMapper;
import com.app.magiclamp.model.mypage.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class OrderHistoryListService {

    @Autowired
    private OrderHistoryMapper orderMapper;

    public OrderHistoryListPage getOrderHistoryList(int userindex, int pageNum, OrderHistorySearchOption option){

            int page = (pageNum -1) * 10;

            log.info("############## service option >> " + option);

            List<OrderHistoryListDTO> list = orderMapper.selectOrderHistoryList(userindex, page, option);

            log.info("################ service List >> " + list);

            int totalCount = orderMapper.selectOrderHistoryListTotalCount(userindex, option);

            OrderHistoryListPage orderHistoryListPage = new OrderHistoryListPage(10, pageNum, list, totalCount, option);

        return orderHistoryListPage;

    }

}
