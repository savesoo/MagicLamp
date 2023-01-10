package com.app.magiclamp.service.administrator;


import com.app.magiclamp.domain.BookListPage;
import com.app.magiclamp.entity.Book;
import com.app.magiclamp.repository.BookAdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
//    public List<Book> search(SearchType searchType) {
//
//        List<Book> bookList = null;
//
//        switch(searchType.getSearchoption()) {
//
//            case "isbn" :
//                bookList = bookAdministratorRepository.findByIsbnContaining(searchType.getKeyword());
//                break;
//            case "title" :
//                bookList = bookAdministratorRepository.findByTitleContaining(searchType.getKeyword());
//                break;
//            case  "author" :
//                bookList = bookAdministratorRepository.findByAuthorContaining(searchType.getKeyword());
//                break;
//            case "publisher" :
//                bookList = bookAdministratorRepository.findByPublisherContaining(searchType.getKeyword());
//                break;
//        }
//
//        return bookList;
//    }

}
