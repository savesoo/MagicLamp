package com.app.magiclamp.controller.mypage;

import com.app.magiclamp.entity.AddrBook;
import com.app.magiclamp.model.AuthUserDTO;
import com.app.magiclamp.service.mypage.AddrBookListService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Log4j2
@Controller
public class AddrBookListController {

    @Autowired
    private AddrBookListService addrBookListService;

    @GetMapping("mypage/addrbook")
    public String selectAddrBookList(
            @RequestParam(value = "page", defaultValue = "1") int pageNum,
            @RequestParam(value = "keyword", defaultValue = "") String recipient,
            @AuthenticationPrincipal AuthUserDTO user,
            Model model){

        model.addAttribute("addrList", addrBookListService.getAddrBookPage(user.getUserindex(), pageNum, recipient));
        if(!recipient.equals(" ") && recipient != null){
            model.addAttribute("keyword", recipient);
        }

        return "view/mypage/addrbook";
    }
}
