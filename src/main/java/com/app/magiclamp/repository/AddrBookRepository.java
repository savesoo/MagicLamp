package com.app.magiclamp.repository;

import com.app.magiclamp.entity.AddrBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AddrBookRepository extends JpaRepository<AddrBook, Integer>, JpaSpecificationExecutor<AddrBook> {

    @Query("select a from AddrBook a where a.userindex = :userindex and a.priority = :priority")
    AddrBook findByUserindexAndPriority(int userindex, int priority);

    @Query("select a from AddrBook a where a.userindex = ?1 order by a.priority DESC, a.addrindex")
    Page<AddrBook> findAllByUserIndex(int userindex, Pageable pageable);

    // 배송 주소록 전체 리스트
//    @Query("select a from AddrBook a where a.userindex = ?1 order by a.priority DESC, a.addrindex")
//    List<AddrBook> findByUserindex(int userindex);

    // 배송 주소록 주소 업데이트
    @Transactional
    @Modifying
    @Query("""
            update AddrBook a set a.postnum = ?1, a.address1 = ?2, a.address2 = ?3 where a.userindex = ?4 and a.priority = 1""")
    int updateAddrBookUserInfoAddress(String postnum, String address1, String address2, int userindex);

    // 배송 주소록 전화번호 업데이트
    @Transactional
    @Modifying
    @Query("""
            update AddrBook a set a.phone = ?1 where a.userindex = ?2 and a.priority = 1""")
    int updateAddrBookUserInfoPhone(String phone, int userindex);

    // 배송 주소록 삭제
    @Transactional
    @Modifying
    @Query("delete from AddrBook a where a.addrindex = ?1 and a.userindex = ?2 and a.priority = 0")
    int deleteByAddrindex(int addrindex, int userindex);

}
