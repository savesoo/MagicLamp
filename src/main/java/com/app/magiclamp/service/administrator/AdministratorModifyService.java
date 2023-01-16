package com.app.magiclamp.service.administrator;

import com.app.magiclamp.entity.Book;
import com.app.magiclamp.model.administrator.BookModifyRequest;
import com.app.magiclamp.repository.BookAdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class AdministratorModifyService {

    @Autowired
    private BookAdministratorRepository bookAdministratorRepository;

    public int modify(BookModifyRequest bookModifyRequest) {

        MultipartFile multipartFile = bookModifyRequest.getNewimg();

        File saveDir = null;
        String newFileName = null;

        if(multipartFile != null && !multipartFile.isEmpty()) {

            String absolutePath = new File("").getAbsolutePath();

            String path = "bookimg";
            saveDir = new File(absolutePath, path);

            if(!saveDir.exists()) {
                saveDir.mkdirs();
            }

            String uuid = UUID.randomUUID().toString();
            newFileName = uuid + multipartFile.getOriginalFilename();
            File newFile = new File(saveDir, newFileName);

            try {
                multipartFile.transferTo(newFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        Book book = bookModifyRequest.toBookEntity();

        if(newFileName != null) {
            book.setBookimg(newFileName);

        } else {
            book.setBookimg(null);
        }

        int result = 0;

        try {

            bookAdministratorRepository.save(book);

            String oldFileName = bookModifyRequest.getOldimg();

            if(newFileName != null && oldFileName != null && !oldFileName.isEmpty()) {
                File delOldFile = new File(saveDir, oldFileName);

                if(delOldFile.exists()) {
                    delOldFile.delete();
                }
            }

        } catch (Exception e) {

            if(newFileName != null) {
                File delFile = new File(saveDir, newFileName);

                if(delFile.exists()) {
                    delFile.delete();
                }

            }
        }

        return result;
    }
}
