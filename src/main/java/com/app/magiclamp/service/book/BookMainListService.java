package com.app.magiclamp.service.book;

import com.app.magiclamp.entity.Book;
import com.app.magiclamp.mapper.BookMainListMapper;
import com.app.magiclamp.model.BookMainListPage;
import com.app.magiclamp.model.BookSearchOption;
import com.app.magiclamp.repository.BookRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class BookMainListService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMainListMapper bookMainListMapper;

    public BookMainListPage getBookMainPageList(int pagenum, String searchType, String keyword) {

        Page<Book> page = bookRepository.findAll(PageRequest.of(pagenum - 1, 10, Sort.by("pubdate").descending()));

        // 도서 리스트
        List<Book> list = page.getContent();

        // 전체 도서의 개수
        int totalCount = (int) page.getTotalElements();

        BookMainListPage bookMainListPage = new BookMainListPage(10, pagenum, list, totalCount, searchType, keyword);

        return bookMainListPage;
    }

    public BookMainListPage getBookMainPageSearchList(int pagenum, BookSearchOption searchOption) {

        BookMainListPage bookMainListPage = null;

        int stnum = (pagenum - 1) * 10;

        // 도서 리스트
        List<Book> list = bookMainListMapper.selectByOption(stnum, searchOption);

        // 전체 도서의 개수
        log.info("전체 도서의 개수 =================>" + bookMainListMapper.selectByOptionTotalCount(searchOption));
        int totalCount = bookMainListMapper.selectByOptionTotalCount(searchOption);

        bookMainListPage = new BookMainListPage(10, pagenum, list, totalCount, searchOption.getSearchType(), searchOption.getKeyword());

        return bookMainListPage;
    }

}
