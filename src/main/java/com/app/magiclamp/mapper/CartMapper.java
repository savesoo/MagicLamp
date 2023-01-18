package com.app.magiclamp.mapper;

import com.app.magiclamp.entity.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CartMapper {

    @Select("select * from tbl_cart where isbn = #{p1} and userindex = #{p2} ")
    Cart searchIsbn(@Param("p1") String isbnCart, @Param("p2") Integer userindexCart);

}
