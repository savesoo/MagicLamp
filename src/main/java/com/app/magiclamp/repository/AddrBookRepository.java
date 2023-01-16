package com.app.magiclamp.repository;

import com.app.magiclamp.entity.AddrBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AddrBookRepository extends JpaRepository<AddrBook, Integer> {

    @Query("select a from AddrBook a where a.userindex = :userindex and a.priority = :priority")
    AddrBook findByUserindexAndPriority(int userindex, int priority);


    // 배송 주소록 전체 리스트
    @Query("select a from AddrBook a where a.userindex = ?1")
    List<AddrBook> findByUserindex(int userindex);

    // 배송 주소록 업데이트
    @Transactional
    @Modifying
    @Query("""
            update AddrBook a set a.addrname = ?1, a.recipient = ?2, a.phone = ?3, a.postnum = ?4, a.address1 = ?5, a.address2 = ?6, a.priority = ?7
            where a.addrindex = ?8""")
    int updateAddrBookByAddrindex(String addrname, String recipient, String phone, String postnum, String address1, String address2, int priority, int addrindex);

    // 배송 주소록 삭제
    @Transactional
    @Modifying
    @Query("delete from AddrBook a where a.addrindex = ?1")
    int deleteByAddrindex(int addrindex);

}
