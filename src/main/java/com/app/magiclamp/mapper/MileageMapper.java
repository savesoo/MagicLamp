package com.app.magiclamp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface MileageMapper {

    @Select("SELECT IFNULL((SUM(mileage) - SUM(usemileage)),0) AS currMileage FROM tbl_mileage WHERE userindex =#{userindex}")
    Map<String, Integer> selectCurrentMileage(@Param("userindex") int userindex);
}
