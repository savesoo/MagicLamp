package com.app.magiclamp.mapper;

import com.app.magiclamp.model.bookMain.BookRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BookMapper {

    @Select("select c.cnt, r.star, b.* from tbl_book b left outer join (SELECT isbn, round(avg(star)) as star FROM project_lamp.tbl_review group by isbn order by isbn) r on b.isbn=r.isbn\n" +
            " left outer join (select isbn, count(isbn) as cnt from tbl_review group by isbn) c on b.isbn=c.isbn where b.isbn=#{isbn}")
    BookRequest selectBookAlpha(String isbn);
}
