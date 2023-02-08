package com.app.magiclamp.service.administrator;

import com.app.magiclamp.entity.Book;
import com.app.magiclamp.repository.BookAdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class AdministratorDeleteService {

    @Autowired
    private BookAdministratorRepository bookAdministratorRepository;

    public int deleteBook(String isbn) {

        Book book = bookAdministratorRepository.findById(isbn).get();

        int result = bookAdministratorRepository.deleteByIsbn(isbn);

        String absolutePath = new File("").getAbsolutePath();
        String path = "photo";
        File delDir = new File(absolutePath, path);

        if(result>0 && book.getBookimg() != null) {


            File delFile = new File(delDir, File.separator+book.getBookimg());

            if (delFile.exists()) {

                delFile.delete();
            }
        }

        return result;
    }

}
