package com.app.magiclamp.controller.book;

import com.app.magiclamp.model.user.AuthUserDTO;
import com.app.magiclamp.service.review.ReviewReadService;
import com.app.magiclamp.service.book.BookViewService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/main/product")
@Log4j2
public class BookViewController {

    @Autowired
    private BookViewService bookViewService;

    @Autowired
    private ReviewReadService reviewReadService;

    @GetMapping
    public String getBookView(
            @RequestParam("isbn") String isbn,
            @RequestParam("p") int pageNum,
            Model model,
            HttpServletRequest request,
            @AuthenticationPrincipal AuthUserDTO authUserDTO
            ) {

        model.addAttribute("pageNum", pageNum);
        model.addAttribute("bookView", bookViewService.selectBook(isbn));
        model.addAttribute("bookViewAlpha", bookViewService.selectBookAlpha(isbn));
        log.info("authUserDTO........... ===> " + authUserDTO);

        model.addAttribute("avg", reviewReadService.calculateTheAvg(isbn));
        model.addAttribute("people", reviewReadService.countPeople(isbn));
        model.addAttribute("star5", reviewReadService.count5(isbn));
        model.addAttribute("star4", reviewReadService.count4(isbn));
        model.addAttribute("star3", reviewReadService.count3(isbn));
        model.addAttribute("star2", reviewReadService.count2(isbn));
        model.addAttribute("star1", reviewReadService.count1(isbn));
        model.addAttribute("loginInfo", authUserDTO == null? -1 : authUserDTO.getUserindex());
        model.addAttribute("myReview", reviewReadService.readMyReview(isbn, authUserDTO == null? -1 : authUserDTO.getUserindex()));

        log.info("bookViewService.selectBook(isbn) >>>>>>>>>>>>>>>>> " + bookViewService.selectBook(isbn));

        return "view/book/bookView";
    }
}
