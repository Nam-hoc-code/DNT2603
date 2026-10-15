package com.example.learnspringboot.bai2.controller;

import com.example.learnspringboot.bai2.entity.Account;
import com.example.learnspringboot.bai2.service.IAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final IAccountService accountService;

    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAll() {
        return accountService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getById(@PathVariable Integer id) {
        return accountService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Account> getByUsername(@PathVariable String username) {
        return accountService.getByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Account> create(@RequestBody Account account) {
        try {
            return ResponseEntity.ok(accountService.create(account));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> update(@PathVariable Integer id, @RequestBody Account account) {
        try {
            return ResponseEntity.ok(accountService.update(id, account));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (accountService.getById(id).isPresent()) {
            accountService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/department/{deptId}")
    public List<Account> getByDepartmentId(@PathVariable Integer deptId) {
        return accountService.getByDepartmentId(deptId);
    }

    @GetMapping("/position/{posId}")
    public List<Account> getByPositionId(@PathVariable Integer posId) {
        return accountService.getByPositionId(posId);
    }
}