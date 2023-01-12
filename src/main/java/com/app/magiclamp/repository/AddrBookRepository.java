package com.app.magiclamp.repository;

import com.app.magiclamp.entity.AddrBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddrBookRepository extends JpaRepository<AddrBook, Integer> {

    @Query("select a from AddrBook a where a.userindex = :userindex and a.priority = :priority")
    AddrBook findByUserindexAndPriority(int userindex, int priority);


}
