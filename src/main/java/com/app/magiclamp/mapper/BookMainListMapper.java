package com.app.magiclamp.mapper;

import com.app.magiclamp.model.bookMain.BookRequest;
import com.app.magiclamp.model.bookMain.BookSearchOption;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMainListMapper {

    //
    List<BookRequest> selectByOption(@Param("p1") int stnum, @Param("p2") BookSearchOption searchOption);

    // 전체 수량
    int selectByOptionTotalCount(BookSearchOption searchOption);

}
