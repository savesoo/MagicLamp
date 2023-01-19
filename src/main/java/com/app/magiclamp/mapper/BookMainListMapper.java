package com.app.magiclamp.mapper;

import com.app.magiclamp.entity.Book;
import com.app.magiclamp.model.bookMain.BookRequest;
import com.app.magiclamp.model.bookMain.BookSearchOption;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Mapper
public interface BookMainListMapper {

    List<BookRequest> selectByOption(@Param("p1") int stnum, @Param("p2") BookSearchOption searchOption);

    int selectByOptionTotalCount(BookSearchOption searchOption);

    @Select("select r.star, b.* from tbl_book b left outer join tbl_review r on b.isbn=r.isbn order by pubdate desc limit #{param1}, #{param2}")
    List<BookRequest> selectBookStar(int i, int i1);
}
