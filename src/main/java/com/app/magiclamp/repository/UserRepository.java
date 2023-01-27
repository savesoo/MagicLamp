package com.app.magiclamp.repository;

import com.app.magiclamp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.username = :username")
    Optional<User> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("update User u set u.password = ?1 where u.username = ?2")
    int updatePasswordByUsername(String password, String username);

    @Transactional
    @Modifying
    @Query("update User u set u.phone = ?1 where u.username = ?2")
    int updatePhoneByUsername(String phone, String username);

    @Transactional
    @Modifying
    @Query("update User u set u.postnum = ?1, u.address1 = ?2, u.address2 = ?3 where u.userindex = ?4")
    int updateAddressByUsername(String postnum, String address1, String address2, int userindex);

    @Transactional
    @Modifying
    @Query("update User u set u.postnum = ?1, u.address1 = ?2, u.address2 = ?3, u.phone = ?4 where u.userindex = ?5")
    int updateAddressByAddrBook(String postnum, String address1, String address2, String phone, int userindex);

    @Transactional
    @Modifying
    @Query("update User u set u.deleted = 1 where u.username = ?1 and u.userindex = ?2")
    int updateUserWithdraw(String username, int userindex);





}
