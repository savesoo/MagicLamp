package com.app.magiclamp.mapper;

import com.app.magiclamp.entity.Book;
import com.app.magiclamp.model.BookSearchOption;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Page;

@Mapper
public interface BookMainListMapper {

    Page<Book> selectByOption(BookSearchOption searchOption);

}
