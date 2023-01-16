package com.app.magiclamp.mapper;

import com.app.magiclamp.entity.Book;
import com.app.magiclamp.model.BookSearchOption;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMainListMapper {

    List<Book> selectByOption(@Param("p1") int stnum, @Param("p2") BookSearchOption searchOption);

    int selectByOptionTotalCount(BookSearchOption searchOption);

}
