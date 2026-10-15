package com.example.learnspringboot.bai2.service;

import com.example.learnspringboot.bai2.entity.Account;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    List<Account> getAll();
    Optional<Account> getById(Integer id);
    Optional<Account> getByUsername(String username);
    Account create(Account account);
    Account update(Integer id, Account updated);
    void delete(Integer id);
    List<Account> getByDepartmentId(Integer deptId);
    List<Account> getByPositionId(Integer posId);
    List<com.example.learnspringboot.bai2.dto.AccountDTO> findAllAccounts();
    void create(com.example.learnspringboot.bai2.dto.request.AccoutnDTOResquest request);
    Boolean update(Integer id, com.example.learnspringboot.bai2.dto.request.AccoutnDTOResquest request);
}