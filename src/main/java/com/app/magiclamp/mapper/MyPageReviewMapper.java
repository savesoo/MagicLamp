package com.app.magiclamp.mapper;

import com.app.magiclamp.model.mypage.MyPageReviewListDTO;
import com.app.magiclamp.model.mypage.MyReviewSearchOption;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface MyPageReviewMapper {

    @Select("SELECT IFNULL(SUM(mileage),0) AS totalSave, IFNULL(SUM(usemileage),0) AS totalUse FROM tbl_mileage WHERE userindex =#{userindex}")
    Map<String, Integer> selectTotalMileage(@Param("userindex") int userindex);

    @Select("SELECT IFNULL(SUM(mileage)-SUM(usemileage), 0) AS expiredmileage " +
            "FROM tbl_mileage " +
            "WHERE DATE_ADD(updatedate, INTERVAL +1 YEAR) < DATE_ADD(NOW(),INTERVAL +30 DAY) " +
            "AND userindex=#{userindex}")
    Integer expiredMileage(@Param("userindex") int userindex);

//    @Select("SELECT r.*, b.title as title, b.bookimg as bookimg" +
//            "FROM tbl_review r" +
//            "INNER JOIN tbl_book b" +
//            "ON r.isbn = b.isbn" +
//            "WHERE r.reviewer = #{userindex}")
    List<MyPageReviewListDTO> selectMyPageReviewList(@Param("userindex") int userindex,
                                                     @Param("page") int page,
                                                     @Param("option") MyReviewSearchOption option);

    int selectMyPageReviewListTotalCount(@Param("userindex") int userindex,
                                         @Param("option")MyReviewSearchOption option);

}
