package com.app.magiclamp.mapper;

import com.app.magiclamp.entity.Book;
import com.app.magiclamp.model.BookSearchOption;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper
public interface BookMainListMapper {

    List<Book> selectByOption(BookSearchOption searchOption);

    int selectByOptionTotalCount(BookSearchOption searchOption);

}
