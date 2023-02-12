package com.app.magiclamp.mapper;

import com.app.magiclamp.model.book.BookInfoDTO;
import com.app.magiclamp.model.mypage.OrderDetailAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDetailMapper {

    List<BookInfoDTO> orderDetailBookInfo(@Param("orderindex") int orderindex);

    OrderDetailAddress orderDetailAddr(@Param("userindex") int userindex,
                                       @Param("orderindex") int orderindex);

}
