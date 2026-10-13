package com.example.learnspringboot.bai2.service;

import com.example.learnspringboot.bai2.entity.Account;
import com.example.learnspringboot.bai2.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    public Optional<Account> getById(Integer id) {
        return accountRepository.findById(id);
    }

    public Optional<Account> getByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public Account create(Account account) {
        if (accountRepository.existsByUsername(account.getUsername())) {
            throw new RuntimeException("Username already exists: " + account.getUsername());
        }
        if (accountRepository.existsByEmail(account.getEmail())) {
            throw new RuntimeException("Email already exists: " + account.getEmail());
        }
        return accountRepository.save(account);
    }

    public Account update(Integer id, Account updated) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found: " + id));
        account.setEmail(updated.getEmail());
        account.setFullName(updated.getFullName());
        account.setDepartment(updated.getDepartment());
        account.setPosition(updated.getPosition());
        account.setStatus(updated.getStatus());
        if (updated.getPassword() != null && !updated.getPassword().isEmpty()) {
            account.setPassword(updated.getPassword());
        }
        return accountRepository.save(account);
    }

    public void delete(Integer id) {
        accountRepository.deleteById(id);
    }

    public List<Account> getByDepartmentId(Integer deptId) {
        return accountRepository.findByDepartmentId(deptId);
    }

    public List<Account> getByPositionId(Integer posId) {
        return accountRepository.findByPositionId(posId);
    }
}