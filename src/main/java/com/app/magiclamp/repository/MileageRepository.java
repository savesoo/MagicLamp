package com.app.magiclamp.repository;

import com.app.magiclamp.entity.Mileage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface MileageRepository extends JpaRepository<Mileage, Integer>, JpaSpecificationExecutor<Mileage> {

    @Query("select m from Mileage m where m.userindex = :userindex ")
    Mileage findByUserindex(Integer userindex);





}
