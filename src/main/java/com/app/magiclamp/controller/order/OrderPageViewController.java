package com.app.magiclamp.controller.order;

import com.app.magiclamp.model.AuthUserDTO;
import com.app.magiclamp.model.order.RequestOrderBook;
import com.app.magiclamp.model.order.RequestPaymentBook;
import com.app.magiclamp.service.order.OrderPageViewService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/order")
public class OrderPageViewController {

    @Autowired
    private OrderPageViewService orderPageViewService;

//    @Autowired
//    private OrderInsertService orderInsertService;

    // 어떻게 list로 받을지 고민하기
    @GetMapping
    public String getOrderPage(@AuthenticationPrincipal AuthUserDTO userDTO,
                        RequestOrderBook orders,
                        Model model){

        log.info(" get order controller ... ");
        log.info(" controller order >>> " + orders);

        // 임시 null 처리
        if(orders==null || userDTO == null){
            return "redirect:/view/book/bookMainList";
        }

        model.addAttribute("userInfo", userDTO); // 로그인 확인용, 추후 삭제
        model.addAttribute("ordersInfo", orderPageViewService.getOrderBook(orders, userDTO.getUserindex()));


        return "view/order/order";

    }


    @PostMapping("/payment")
    public String insertOrder(@AuthenticationPrincipal AuthUserDTO userDTO,
                              RequestPaymentBook paymentBook){

        log.info(" post order ... ");
        log.info( "user >>> " + userDTO);
        log.info(" insert 전 order >>>>>> " + paymentBook);

        if(paymentBook==null){
            return "redirect:/view/book/mainList";
        }

        //orderInsertService.Order(paymentBook, userDTO.getUserindex());

        log.info("insert 후 order >>>>>> " + paymentBook);

        return "redirect:/";
    }


}
