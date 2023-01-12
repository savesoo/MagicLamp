package com.app.magiclamp.service.book;

import com.app.magiclamp.entity.Book;
import com.app.magiclamp.repository.BookRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class BookViewService {

    @Autowired
    private BookRepository bookRepository;

    public Book selectBook(String isbn) {

        return bookRepository.findById(isbn).get();
    }
}
