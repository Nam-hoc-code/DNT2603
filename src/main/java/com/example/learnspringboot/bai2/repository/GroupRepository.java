package com.example.learnspringboot.bai2.repository;

import com.example.learnspringboot.bai2.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Integer> {
    boolean existsByName(String name);
    
    @Query("SELECT g FROM Group g LEFT JOIN FETCH g.accountGroups ag LEFT JOIN FETCH ag.account WHERE g.id = :id")
    Optional<Group> findByIdWithAccounts(@Param("id") Integer id);
}