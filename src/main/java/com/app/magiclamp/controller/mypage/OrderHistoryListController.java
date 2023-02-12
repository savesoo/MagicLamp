package com.app.magiclamp.controller.mypage;

import com.app.magiclamp.model.user.AuthUserDTO;
import com.app.magiclamp.model.mypage.OrderHistorySearchOption;
import com.app.magiclamp.service.mypage.OrderHistoryListService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Log4j2
public class OrderHistoryListController {

    @Autowired
    private OrderHistoryListService orderHistoryListService;

    @GetMapping("/mypage/orderhistory")
    public String getOrderHistoryList(
            @RequestParam(value = "page", defaultValue = "1") int pageNum,
            OrderHistorySearchOption option,
            @AuthenticationPrincipal AuthUserDTO user,
            Model model
    ) {

        int userindex = user.getUserindex();

        model.addAttribute("orderList", orderHistoryListService.getOrderHistoryList(userindex, pageNum, option));
        model.addAttribute("option", option);
        log.info("######################" + option);


        return "view/mypage/orderHistory";

    }
}
