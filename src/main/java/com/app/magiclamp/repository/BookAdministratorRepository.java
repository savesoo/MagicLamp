package com.app.magiclamp.repository;

import com.app.magiclamp.entity.Book;
import com.app.magiclamp.model.administrator.BookListPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface BookAdministratorRepository extends JpaRepository<Book, String> {


    // 삭제
    @Transactional
    @Modifying
    @Query("delete from Book b where b.isbn = :isbn")
    int deleteByIsbn(@Param("isbn") String isbn);

    // 검색
    Page<Book> findByIsbnContaining(String keyword, Pageable pageable);
    Page<Book> findByTitleContaining(String keyword, Pageable pageable);
    Page<Book> findByAuthorContaining(String keyword, Pageable pageable);
    Page<Book> findByPublisherContaining(String keyword, Pageable pageable);

}
