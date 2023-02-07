package com.app.magiclamp.service.mypage;

import com.app.magiclamp.entity.AddrBook;
import com.app.magiclamp.entity.Book;
import com.app.magiclamp.mapper.OrderDetailMapper;
import com.app.magiclamp.model.BookInfoDTO;
import com.app.magiclamp.model.mypage.OrderDetailAddress;
import com.app.magiclamp.model.order.OrderBookPageDTO;
import com.app.magiclamp.model.order.RequestOrderBook;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    public OrderBookPageDTO getOrderDetail(int userindex, int orderindex) {

        OrderBookPageDTO orderBookPageDTO = new OrderBookPageDTO();

        List<BookInfoDTO> itemList = orderDetailMapper.orderDetailBookInfo(orderindex);

        orderBookPageDTO.addToBookInfos(itemList);

        // 총액 합산
        orderBookPageDTO.calOrderDetailPrice();

        // 배송 주소
        orderBookPageDTO.setOrderDetailAddr(orderDetailMapper.orderDetailAddr(userindex, orderindex));

        log.info("view order detail >>> " + orderBookPageDTO);

        return orderBookPageDTO;
    }
}
