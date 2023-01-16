package com.app.magiclamp.mapper;

import com.app.magiclamp.model.BookListPage;
import com.app.magiclamp.model.BookSearchOption;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMainListMapper {

    BookListPage selectByOption(BookSearchOption searchOption);

}
