package com.example.learnspringboot.bai2.repository;

import com.example.learnspringboot.bai2.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);
    Optional<Account> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    @Query("SELECT a FROM Account a WHERE a.department.id = :deptId")
    List<Account> findByDepartmentId(@Param("deptId") Integer deptId);

    @Query("SELECT a FROM Account a WHERE a.position.id = :posId")
    List<Account> findByPositionId(@Param("posId") Integer posId);
}

