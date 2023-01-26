package com.app.magiclamp.controller.mypage;

import com.app.magiclamp.model.AuthUserDTO;
import com.app.magiclamp.service.mypage.MileageListService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
public class MileageListController {

    @Autowired
    private MileageListService mileageListService;

    @GetMapping("mypage/mileage")
    public String selectMileageList(
            @RequestParam(value = "page", defaultValue = "1") int pageNum,
            @RequestParam(value = "keyword", defaultValue = "0") int keyword,
            @AuthenticationPrincipal AuthUserDTO user,
            Model model){

        log.info("$$$$$$$$$$$$$$$$$$ pagenum" + pageNum);
        log.info("$$$$$$$$$$$$$$$$$$ keyword" + keyword);
        model.addAttribute("mileList", mileageListService.getMileagePage(user.getUserindex(), pageNum, keyword));
        if(!(keyword <0)){
            model.addAttribute("keyword", keyword);
        }

        return "view/mypage/mileage";
    }
}
