package com.app.magiclamp.controller.mypage;

import com.app.magiclamp.model.AuthUserDTO;
import com.app.magiclamp.model.mypage.MyReviewSearchOption;
import com.app.magiclamp.service.mypage.MyPageReviewListService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Log4j2
public class MyPageReviewListController {

    @Autowired
    private MyPageReviewListService myPageReviewListService;
    @GetMapping("/mypage/myreview")
    public String getMyReviewList(
            @RequestParam(value = "page", defaultValue = "1") int pageNum,
            MyReviewSearchOption option,
            @AuthenticationPrincipal AuthUserDTO user,
            Model model
    ) {

        int userindex = user.getUserindex();
        String searchTitle = option.getSearchTitle();

        log.info("######################" + option);
        model.addAttribute("reviewList", myPageReviewListService.getMyPageReviewList(userindex, pageNum, option));
        model.addAttribute("option", option);


        return "view/mypage/myReview";

    }
}
