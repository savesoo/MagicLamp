package com.app.magiclamp.controller.order;

import com.app.magiclamp.entity.AddrBook;
import com.app.magiclamp.entity.Book;
import com.app.magiclamp.entity.Mileage;
import com.app.magiclamp.model.AuthUserDTO;
import com.app.magiclamp.model.order.RequestOrderBook;
import com.app.magiclamp.service.cart.order.OrderPageViewService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/order")
public class OrderPageViewController {

    @Autowired
    private OrderPageViewService orderPageViewService;

    @PostMapping
    public ModelAndView getOrderPage(@AuthenticationPrincipal AuthUserDTO userDTO,
                                     @RequestBody List<RequestOrderBook> orders) {

        log.info("order >>> " + orders);

        ModelAndView mav = new ModelAndView();
        mav.clear();
        mav.addObject("userInfo", userDTO);



        // 화면 출력용 코드 나중에 삭제
        AddrBook add = orderPageViewService.getUserAddress(userDTO.getUserindex());
        mav.addObject("userAddress", add);
        Mileage mileage = orderPageViewService.getCurrentMileage(userDTO.getUserindex());
        mav.addObject("currMileage", mileage);

        mav.setViewName("view/order/order");
        return mav;

    }


    /*@PostMapping
    public String postOrderForm(){

        return "redirect:/list";
    }*/



}
