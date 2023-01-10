package com.app.magiclamp.service.administrator;


import com.app.magiclamp.domain.BookListPage;
import com.app.magiclamp.domain.SearchOption;
import com.app.magiclamp.entity.Book;
import com.app.magiclamp.repository.BookAdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdministratorListService {

    @Autowired
    private BookAdministratorRepository bookAdministratorRepository;

    public List<Book> getBookList(int pagenum) {

        int index = (pagenum-1)*10;
        int count = 10;

        return bookAdministratorRepository.findAll(index, count);
    }

    public BookListPage getPage(int pagenum) {

        Page<Book> page = bookAdministratorRepository.findAll(PageRequest.of(pagenum-1, 10, Sort.by("regdate").descending()));

        List<Book> book = page.getContent();

        int totalcount = (int) page.getTotalElements();

        BookListPage bookListPage = new BookListPage(10, pagenum, book, totalcount);

        return bookListPage;
    }

//    @Transactional
//    public List<Book> search(SearchOption searchType) {
//
//        List<Book> bookList = null;
//
//        switch(searchType.getSearchoption()) {
//
//            case "isbn" :
//                bookList = bookAdministratorRepository.findByIsbn(searchType.getKeyword());
//                break;
//            case "title" :
//                bookList = bookAdministratorRepository.findByTitle(searchType.getKeyword());
//                break;
//            case  "author" :
//                bookList = bookAdministratorRepository.findByAuthor(searchType.getKeyword());
//                break;
//            case "publisher" :
//                bookList = bookAdministratorRepository.findByPublisher(searchType.getKeyword());
//                break;
//        }
//
//        return bookList;
//    }

}
