package com.app.magiclamp.service.administrator;

import com.app.magiclamp.entity.Book;
import com.app.magiclamp.repository.BookAdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorReadService {

    @Autowired
    private BookAdministratorRepository bookAdministratorRepository;

    public Book readBook(String isbn) {

        return bookAdministratorRepository.findById(isbn).get();
    }

}
