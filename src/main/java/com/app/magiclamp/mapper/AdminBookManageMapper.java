package com.app.magiclamp.mapper;

import com.app.magiclamp.entity.Cart;
import com.app.magiclamp.model.book.BookRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AdminBookManageMapper {

    List<BookRequest> selectBookManageList(@Param("stock") int stock, @Param("isbn") String isbn);

    @Update("update tbl_book set stock = #{stock} where isbn=#{isbn}")
    int updateBookStock(@Param("stock") int stock, @Param("isbn") String isbn);

    @Update("update tbl_book set mileagerate = #{mileagerate} where isbn=#{isbn}")
    int updateBookMilerate(@Param("mileagerate") int mileagerate, @Param("isbn") String isbn);

}
