package com.app.magiclamp.controller.api;

import com.app.magiclamp.entity.Book;
import com.app.magiclamp.service.book.AladinOpenApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class AladinApiController {
    @Autowired
    private AladinOpenApi api;

    @GetMapping("/search/newBook")
    public ArrayList<Book> getNewBook() {
        return api.mainNewBook();
    }

    @GetMapping("/search/bestseller")
    public ArrayList<Book> getBest(){
        return api.bestSeller();
    }
}
