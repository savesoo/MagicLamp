package com.app.magiclamp.mapper;

import com.app.magiclamp.model.mypage.OrderHistoryListDTO;
import com.app.magiclamp.model.mypage.OrderHistorySearchOption;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderHistoryMapper {

    List<OrderHistoryListDTO> selectOrderHistoryList(@Param("userindex") int userindex,
                                                     @Param("page") int page,
                                                     @Param("option") OrderHistorySearchOption option);
    int selectOrderHistoryListTotalCount(@Param("userindex") int userindex,
                                         @Param("option")OrderHistorySearchOption option);

}
