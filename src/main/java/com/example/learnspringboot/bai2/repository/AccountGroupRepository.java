package com.example.learnspringboot.bai2.repository;

import com.example.learnspringboot.bai2.entity.AccountGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountGroupRepository extends JpaRepository<AccountGroup, Integer> {
    Optional<AccountGroup> findByAccountIdAndGroupId(@Param("accountId") Integer accountId, @Param("groupId") Integer groupId);
    
    List<AccountGroup> findByAccountId(@Param("accountId") Integer accountId);
    
    List<AccountGroup> findByGroupId(@Param("groupId") Integer groupId);
    
    boolean existsByAccountIdAndGroupId(@Param("accountId") Integer accountId, @Param("groupId") Integer groupId);
    
    void deleteByAccountIdAndGroupId(@Param("accountId") Integer accountId, @Param("groupId") Integer groupId);
}