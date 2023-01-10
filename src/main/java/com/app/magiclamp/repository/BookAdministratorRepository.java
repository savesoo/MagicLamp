package com.app.magiclamp.repository;

import com.app.magiclamp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface BookAdministratorRepository extends JpaRepository<Book, String> {

    // 전체 리스트
    @Query("select b from Book b")
    List<Book> findAll(int index, int count);

    // 검색
    List<Book> findByIsbnContaining(String keyword);
    List<Book> findByTitleContaining(String keyword);
    List<Book> findByAuthorContaining(String keyword);
    List<Book> findByPublisherContaining(String keyword);


}
