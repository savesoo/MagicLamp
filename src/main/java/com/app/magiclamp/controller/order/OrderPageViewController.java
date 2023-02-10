package com.app.magiclamp.controller.order;

import com.app.magiclamp.model.AuthUserDTO;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/order")
public class OrderPageViewController {

    @Autowired
    private OrderPageViewService orderPageViewService;

    @GetMapping
    public String getOrderPageView(HttpServletRequest req,
                                   RequestOrderBook orders,
                                   @AuthenticationPrincipal AuthUserDTO user,
                                   Model model){

        log.info(" >>>>>> get order controller >>>>>> ");
        log.info(" orders >>>> " + orders);

        if(user == null){
            return "redirect:/main";
        }

        HttpSession session = req.getSession();
        OrderBookPageDTO orderBookPageDTO;

        // 장바구니 -> 주문으로 넘어온 경우 session에서 꺼내어 set
        // 즉시 구매인 경우 get으로 넘어온 정보를 바로 set해줌
        if(session.getAttribute(String.valueOf(user.getUserindex())) != null && orders.getIsbn() == null){
            orderBookPageDTO = (OrderBookPageDTO)session.getAttribute(String.valueOf(user.getUserindex()));
        }
        else {
            // session 사라진 뒤 url 직접 접속하는 경우(id->null)
            if(orders.getIsbn()==null){
                return "redirect:/main";
            }
            // 기존 session 남아있을시 제거
            session.removeAttribute(String.valueOf(user.getUserindex()));
            orderBookPageDTO = orderPageViewService.getOrderBook(orders, user.getUserindex());
        }

        log.info("order2" + orderBookPageDTO);

        model.addAttribute("loginInfo", user.getUserindex()); // user 정보
        model.addAttribute("ordersInfo", orderBookPageDTO);

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
