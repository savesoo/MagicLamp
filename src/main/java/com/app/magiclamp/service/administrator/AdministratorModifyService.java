package com.app.magiclamp.service.administrator;

import com.app.magiclamp.entity.Book;
import com.app.magiclamp.model.administrator.BookModifyRequest;
import com.app.magiclamp.repository.BookAdministratorRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class AdministratorModifyService {

    @Autowired
    private BookAdministratorRepository bookAdministratorRepository;

    public int modify(BookModifyRequest bookModifyRequest) {


        MultipartFile multipartFile = bookModifyRequest.getBookimg();

        File saveDir = null;
        String newFileName = null;

        if(multipartFile != null && !multipartFile.isEmpty()) {

            // 새 이미지 저장
            String absolutePath = new File("").getAbsolutePath();

            String path = "photo";
            saveDir = new File(absolutePath, path);

            // 폴더 없으면 생성
            if(!saveDir.exists()) {
                saveDir.mkdirs();
            }

            // 새 파일 이름 + 저장 경로
            newFileName = bookModifyRequest.getIsbn()+".jpg";
            File newFile = new File(saveDir, newFileName);

            try {
                // 파일 저장
                multipartFile.transferTo(newFile);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        Book book = bookModifyRequest.toBookEntity();
        
        if(newFileName != null && !newFileName.isEmpty()) {
            book.setBookimg(newFileName);
        }

        int result = 0;

        bookAdministratorRepository.save(book);

        return result;
    }
}
