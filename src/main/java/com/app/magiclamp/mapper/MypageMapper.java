package com.app.magiclamp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface MypageMapper {

    @Select("SELECT SUM(mileage) AS totalSave, SUM(usemileage) AS totalUse FROM tbl_mileage WHERE userindex =#{userindex}")
    Map<String, Integer> selectTotalMileage(@Param("userindex") int userindex);

}
