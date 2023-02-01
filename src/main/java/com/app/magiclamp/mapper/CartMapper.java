package com.app.magiclamp.mapper;

import com.app.magiclamp.entity.Cart;
import com.app.magiclamp.model.mypage.CartListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CartMapper {

    @Select("select * from tbl_cart where isbn = #{p1} and userindex = #{p2} ")
    Cart searchIsbn(@Param("p1") String isbnCart, @Param("p2") Integer userindexCart);

    List<CartListDTO> selectCartList(@Param("userindex") int userindex, @Param("option") int option);
}
