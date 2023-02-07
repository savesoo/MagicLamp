package com.app.magiclamp.controller.mypage;

import com.app.magiclamp.model.AuthUserDTO;
import com.app.magiclamp.model.mypage.OrderHistorySearchOption;
import com.app.magiclamp.service.mypage.OrderDetailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@Log4j2
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/mypage/orderDetail")
    public String getOrderHistoryList(
            HttpServletRequest req,
            @RequestParam(value = "orderindex") int orderindex,
            @AuthenticationPrincipal AuthUserDTO user,
            Model model
    ) {

        int userindex = user.getUserindex();

        String referer = req.getHeader("Referer");

        String preUrl = referer.substring(referer.indexOf("/mypage"));

        model.addAttribute("orderDetail", orderDetailService.getOrderDetail(userindex, orderindex));
        model.addAttribute("preUrl", preUrl);

        return "view/mypage/orderDetail";

    }
}
