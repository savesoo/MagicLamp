package com.app.magiclamp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReviewMapper {

    // 평점
    @Select("select round(avg(star), 1) from tbl_review group by isbn having isbn=#{isbn}")
    Double calculateTheAverageOfStar(String isbn);
}
