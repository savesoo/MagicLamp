package com.app.magiclamp.service.administrator;

import com.app.magiclamp.model.administrator.BookInsertRequest;
import com.app.magiclamp.entity.Book;
import com.app.magiclamp.repository.BookAdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class AdministratorInsertService {

    @Autowired
    private BookAdministratorRepository bookAdministratorRepository;

    public int bookInsert(BookInsertRequest bookInsertRequest) {

        MultipartFile file = bookInsertRequest.getBookimg();

        File saveDir = null;
        String newFileName = null;

        if (file != null && !file.isEmpty() && file.getSize() > 0) {

            // 저장경로
            String absolutePath = new File("").getAbsolutePath();

            String path = "photo";
            saveDir = new File(absolutePath, path);

            // 폴더가 없으면 생성
            if (!saveDir.exists()) {
                saveDir.mkdirs();
            }

            // 파일이름 중복확인

            newFileName = bookInsertRequest.getIsbn()+".jpg";

            File newFile = new File(saveDir, newFileName);

            try {
                file.transferTo(newFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        // Entity 저장
        Book book = bookInsertRequest.toBookEntity();

        if (newFileName != null) {
            book.setBookimg(newFileName);
        }

        int result = 0;

        try {
            result = bookAdministratorRepository.save(book) != null ? 1 : 0;

        } catch (Exception e) {

            // 파일이 존재하면 삭제
            if (newFileName != null) {

                File delFile = new File(saveDir, newFileName);

                if (delFile.exists()) {

                    delFile.delete();
                }
            }
        }

        return result;
    }

    @Transactional
    public void checkIsbnDuplication(BookInsertRequest bookSaveRequest) {

        boolean isbnDuplicate = bookAdministratorRepository.existsById(bookSaveRequest.toBookEntity().getIsbn());

        if(isbnDuplicate) {
            throw new IllegalStateException("이미 등록된 책 정보입니다. 다시 확인해주세요.");
        }

    }

}
