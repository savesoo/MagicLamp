package com.app.magiclamp.repository.book;

import com.app.magiclamp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
