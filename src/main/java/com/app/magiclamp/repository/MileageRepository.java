package com.app.magiclamp.repository;

import com.app.magiclamp.entity.Mileage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MileageRepository extends JpaRepository<Mileage, Integer> {

    @Query("select m.mileage from Mileage m where m.userindex = :userindex ")
    Mileage findMileageByUserindex(Integer userindex);


}
