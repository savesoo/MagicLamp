package com.app.magiclamp.controller.order;

import com.app.magiclamp.entity.Book;
import com.app.magiclamp.model.AuthUserDTO;
import com.app.magiclamp.model.mypage.AddrBookRequest;
import com.app.magiclamp.model.order.OrderBookPageDTO;
import com.app.magiclamp.model.order.RequestOrderBook;
import com.app.magiclamp.service.order.OrderPageViewService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/order")
public class OrderPageViewController {

    @Autowired
    private OrderPageViewService orderPageViewService;

    // 어떻게 list로 받을지 고민하기
    @GetMapping
    public String getOrderPage(@AuthenticationPrincipal AuthUserDTO userDTO,
                               RequestOrderBook orders,
                               Model model){

        log.info(" get order controller ... ");
        log.info(" controller order >>> " + orders);

        // 임시 null 처리
        if(orders==null || userDTO == null){

            return "redirect:/main";
        }

        model.addAttribute("loginInfo", userDTO == null? -1 : userDTO.getUserindex()); // user 정보
        model.addAttribute("ordersInfo", orderPageViewService.getOrderBook(orders, userDTO.getUserindex()));


        return "view/order/order";

    }

    @GetMapping("order2")
    public String getOrderPageView(HttpServletRequest req,
                                   @AuthenticationPrincipal AuthUserDTO user,
                                   Model model){

        HttpSession session = req.getSession();

        OrderBookPageDTO orderBookPageDTO = (OrderBookPageDTO)session.getAttribute(String.valueOf(user.getUserindex()));

        if(user == null){
            return "redirect:/main";
        }

        model.addAttribute("loginInfo", user == null? -1 : user.getUserindex()); // user 정보
        model.addAttribute("ordersInfo", orderBookPageDTO);

        session.removeAttribute(String.valueOf(user.getUserindex()));

        return "view/order/order";

    }

    // 1건 또는 N건 받아오는 Post
    @PostMapping
    public ResponseEntity<OrderBookPageDTO> getOrderPagePost(
            HttpServletRequest req,
            @RequestBody List<RequestOrderBook> orders,
            @AuthenticationPrincipal AuthUserDTO user
    ){
        OrderBookPageDTO orderBookPageDTO = orderPageViewService.getOrderBookPost(orders, user.getUserindex());

        HttpSession session = req.getSession();

        if(session.getAttribute(String.valueOf(user.getUserindex())) != null){
            log.info("################session is already used##################");
            session.removeAttribute(String.valueOf(user.getUserindex()));
        }

        session.setAttribute(String.valueOf(user.getUserindex()), orderBookPageDTO);

        return new ResponseEntity<>(orderBookPageDTO, HttpStatus.OK);
    }

}
