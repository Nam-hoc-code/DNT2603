package com.example.learnspringboot.bai2.controller;

import com.example.learnspringboot.bai2.entity.Account;
import com.example.learnspringboot.bai2.entity.AccountGroup;
import com.example.learnspringboot.bai2.entity.Group;
import com.example.learnspringboot.bai2.service.IAccountGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account-groups")
public class AccountGroupController {
    private final IAccountGroupService accountGroupService;

    public AccountGroupController(IAccountGroupService accountGroupService) {
        this.accountGroupService = accountGroupService;
    }

    @PostMapping
    public ResponseEntity<AccountGroup> addAccountToGroup(
            @RequestParam Integer accountId,
            @RequestParam Integer groupId,
            @RequestParam(required = false, defaultValue = "MEMBER") String roleInGroup) {
        try {
            return ResponseEntity.ok(accountGroupService.addAccountToGroup(accountId, groupId, roleInGroup));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/account/{accountId}/groups")
    public ResponseEntity<List<Group>> getGroupsByAccountId(@PathVariable Integer accountId) {
        try {
            return ResponseEntity.ok(accountGroupService.getGroupsByAccountId(accountId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/group/{groupId}/accounts")
    public ResponseEntity<List<Account>> getAccountsByGroupId(@PathVariable Integer groupId) {
        try {
            return ResponseEntity.ok(accountGroupService.getAccountsByGroupId(groupId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/account/{accountId}")
    public List<AccountGroup> getAccountGroupsByAccountId(@PathVariable Integer accountId) {
        return accountGroupService.getAccountGroupsByAccountId(accountId);
    }

    @GetMapping("/group/{groupId}")
    public List<AccountGroup> getAccountGroupsByGroupId(@PathVariable Integer groupId) {
        return accountGroupService.getAccountGroupsByGroupId(groupId);
    }

    @DeleteMapping("/account/{accountId}/group/{groupId}")
    public ResponseEntity<Void> removeAccountFromGroup(
            @PathVariable Integer accountId,
            @PathVariable Integer groupId) {
        try {
            accountGroupService.removeAccountFromGroup(accountId, groupId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/account/{accountId}/group/{groupId}/role")
    public ResponseEntity<AccountGroup> updateRoleInGroup(
            @PathVariable Integer accountId,
            @PathVariable Integer groupId,
            @RequestParam String roleInGroup) {
        try {
            return ResponseEntity.ok(accountGroupService.updateRoleInGroup(accountId, groupId, roleInGroup));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}