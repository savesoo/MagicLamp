package com.app.magiclamp.service.administrator;

import com.app.magiclamp.model.administrator.AdministratorSearchType;
import com.app.magiclamp.model.administrator.BookListPage;
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

    public BookListPage getPage(int pagenum, AdministratorSearchType administratorSearchType) {

        Page<Book> page = null;

        if(administratorSearchType.getKeyword() != null && !administratorSearchType.getKeyword().isEmpty()) {

            switch(administratorSearchType.getSearchoption()) {

                case "isbn" :
                    page = bookAdministratorRepository.findByIsbnContaining(administratorSearchType.getKeyword(), PageRequest.of(pagenum-1, 10, Sort.by("regdate").descending()));
                    break;
                case "title" :
                    page = bookAdministratorRepository.findByTitleContaining(administratorSearchType.getKeyword(), PageRequest.of(pagenum-1, 10, Sort.by("regdate").descending()));
                    break;
                case  "author" :
                    page = bookAdministratorRepository.findByAuthorContaining(administratorSearchType.getKeyword(), PageRequest.of(pagenum-1, 10, Sort.by("regdate").descending()));
                    break;
                case "publisher" :
                    page = bookAdministratorRepository.findByPublisherContaining(administratorSearchType.getKeyword(), PageRequest.of(pagenum-1, 10, Sort.by("regdate").descending()));
                    break;
            }

            List<Book> book = page.getContent();

            int totalcount = (int) page.getTotalElements();

            BookListPage bookListPage = new BookListPage(10, pagenum, book, totalcount);

            return bookListPage;

        } else {

            page = bookAdministratorRepository.findAll(PageRequest.of(pagenum - 1, 10, Sort.by("regdate").descending()));

            List<Book> book = page.getContent();

            int totalcount = (int) page.getTotalElements();

            BookListPage bookListPage = new BookListPage(10, pagenum, book, totalcount);

            return bookListPage;

        }

    }

}
