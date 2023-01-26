package com.app.magiclamp.controller.order;

import com.app.magiclamp.model.AuthUserDTO;
import com.app.magiclamp.model.order.RequestPaymentBook;
import com.app.magiclamp.service.order.OrderInsertService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/order")
public class OrderInsertController {

    @Autowired
    private OrderInsertService orderInsertService;

    @PostMapping("/payment")
    public String insertOrder(@AuthenticationPrincipal AuthUserDTO userDTO,
                              RequestPaymentBook paymentBook){

        log.info(" post order ... ");
        log.info(" user >>> " + userDTO);
        log.info(" >>>>> insert 전 order >>>>>> " + paymentBook);

        if(paymentBook==null){
            return "redirect:/view/book/mainList";
        }

        orderInsertService.Order(paymentBook, userDTO.getUserindex());

        log.info(" >>>>> insert 후 order >>>>>> " + paymentBook);

        return "redirect:/";
    }

}
