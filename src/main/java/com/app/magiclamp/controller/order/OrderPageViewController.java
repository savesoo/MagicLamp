package com.app.magiclamp.controller.order;

import com.app.magiclamp.model.AuthUserDTO;
import com.app.magiclamp.model.order.RequestOrderBook;
import com.app.magiclamp.service.order.OrderPageViewService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
@RequestMapping("/order")
public class OrderPageViewController {

    @Autowired
    private OrderPageViewService orderPageViewService;

    @GetMapping
    public String getob(@AuthenticationPrincipal AuthUserDTO userDTO,
                        RequestOrderBook orders,
                        Model model){


        log.info(" controller order >>> " + orders);

        model.addAttribute("userInfo", userDTO); // 로그인 확인용, 추후 삭제
        model.addAttribute("ordersInfo", orderPageViewService.getOrderBook(orders, userDTO.getUserindex()));


        return "/view/order/order";

    }


}
