package com.app.magiclamp.mapper;

import com.app.magiclamp.model.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReviewMapper {

    // 평점
    @Select("select round(avg(star), 1) from tbl_review group by isbn having isbn=#{isbn}")
    Double calculateTheAverageOfStar(String isbn);

    // 리뷰 목록
    @Select("select * from tbl_review as r left outer join tbl_user as u on r.reviewer=u.userindex where r.isbn=#{isbn} order by r.reviewindex desc limit #{start}, #{end}")
    List<ReviewDTO> getList(@Param("isbn") String isbn, @Param("start") int start, @Param("end") int end);
}
