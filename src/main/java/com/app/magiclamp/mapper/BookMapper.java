package com.app.magiclamp.mapper;

import com.app.magiclamp.model.book.BestsellerDTO;
import com.app.magiclamp.model.book.BookRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMapper {

    @Select("select c.cnt, r.star, b.* from tbl_book b left outer join (SELECT isbn, round(avg(star)) as star FROM project_lamp.tbl_review group by isbn order by isbn) r on b.isbn=r.isbn\n" +
            " left outer join (select isbn, count(isbn) as cnt from tbl_review group by isbn) c on b.isbn=c.isbn where b.isbn=#{isbn}")
    BookRequest selectBookAlpha(String isbn);


    @Select("SELECT bs.totalcnt, b.isbn, b.category, b.title, b.saleprice, b.bookimg FROM (\n" +
            "SELECT isbn, SUM(bookcount) AS totalcnt FROM tbl_orderitem o GROUP BY isbn) bs\n" +
            "INNER JOIN tbl_book b ON bs.isbn = b.isbn\n" +
            "ORDER BY totalcnt DESC, b.title ASC LIMIT 0, 10")
    List<BestsellerDTO> selectBookBestTen();
}