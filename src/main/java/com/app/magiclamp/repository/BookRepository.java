package com.app.magiclamp.repository;

import com.app.magiclamp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {


    // isbn을 요소로 갖는 list를 조건으로 해당하는 도서 모두 불러오기
    // in연산자 사용
    @Query("select b from Book b where b.isbn in :isbns order by b.title")
    List<Book> findByIsbnInOrderByTitleAsc(Collection<String> isbns);
}
