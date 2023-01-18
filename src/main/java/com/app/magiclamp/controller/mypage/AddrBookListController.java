package com.app.magiclamp.controller.mypage;

import com.app.magiclamp.entity.AddrBook;
import com.app.magiclamp.service.mypage.AddrBookListService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String selectAddrBookList(@RequestParam(value = "userindex") int userindex, Model model){
        String newline = System.getProperty("line.separator").toString();
        log.info("UserIndex >>>>>>>>>>>>>>>>>>>> " + userindex);
        List<AddrBook> list = addrBookListService.selectAddrBookAll(userindex);

        log.info("AddrBookList >>>>>>>>>>>>>>>>>>>> " + list);
        log.info("AddrBookListSize >>>>>>>>>>>>>>>>>>>> " + list.size());

        model.addAttribute("addrList", list);
        model.addAttribute("newline", newline);

        return "view/mypage/addrbook";
    }
}
