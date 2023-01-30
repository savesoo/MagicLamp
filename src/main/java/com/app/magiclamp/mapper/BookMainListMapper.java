package com.app.magiclamp.mapper;

import com.app.magiclamp.model.bookMain.BookRequest;
import com.app.magiclamp.model.bookMain.BookSearchOption;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMainListMapper {

    //
    List<BookRequest> selectByOption(@Param("p1") int stnum, BookSearchOption searchOption, String category);

    // 전체 수량
    int selectByOptionTotalCount(BookSearchOption searchOption, String category);

    @Select("select distinct(category) from tbl_book order by category")
    List<String> selectByCategory();
}
