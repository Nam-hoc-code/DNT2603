package com.example.learnspringboot.bai2.service;

import com.example.learnspringboot.bai2.entity.Account;
import com.example.learnspringboot.bai2.entity.AccountGroup;
import com.example.learnspringboot.bai2.entity.Group;
import com.example.learnspringboot.bai2.repository.AccountGroupRepository;
import com.example.learnspringboot.bai2.repository.AccountRepository;
import com.example.learnspringboot.bai2.repository.GroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountGroupService {
    private final AccountGroupRepository accountGroupRepository;
    private final AccountRepository accountRepository;
    private final GroupRepository groupRepository;

    public AccountGroupService(AccountGroupRepository accountGroupRepository,
                                AccountRepository accountRepository,
                                GroupRepository groupRepository) {
        this.accountGroupRepository = accountGroupRepository;
        this.accountRepository = accountRepository;
        this.groupRepository = groupRepository;
    }

    @Transactional
    public AccountGroup addAccountToGroup(Integer accountId, Integer groupId, String roleInGroup) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found: " + accountId));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found: " + groupId));

        if (accountGroupRepository.existsByAccountIdAndGroupId(accountId, groupId)) {
            throw new RuntimeException("Account already in group");
        }

        AccountGroup accountGroup = new AccountGroup();
        accountGroup.setAccount(account);
        accountGroup.setGroup(group);
        accountGroup.setRoleInGroup(roleInGroup != null ? roleInGroup : "MEMBER");
        
        return accountGroupRepository.save(accountGroup);
    }

    public List<Group> getGroupsByAccountId(Integer accountId) {
        List<AccountGroup> accountGroups = accountGroupRepository.findByAccountId(accountId);
        return accountGroups.stream()
                .map(AccountGroup::getGroup)
                .collect(Collectors.toList());
    }

    public List<Account> getAccountsByGroupId(Integer groupId) {
        List<AccountGroup> accountGroups = accountGroupRepository.findByGroupId(groupId);
        return accountGroups.stream()
                .map(AccountGroup::getAccount)
                .collect(Collectors.toList());
    }

    public List<AccountGroup> getAccountGroupsByAccountId(Integer accountId) {
        return accountGroupRepository.findByAccountId(accountId);
    }

    public List<AccountGroup> getAccountGroupsByGroupId(Integer groupId) {
        return accountGroupRepository.findByGroupId(groupId);
    }

    @Transactional
    public void removeAccountFromGroup(Integer accountId, Integer groupId) {
        if (!accountGroupRepository.existsByAccountIdAndGroupId(accountId, groupId)) {
            throw new RuntimeException("Account not in this group");
        }
        accountGroupRepository.deleteByAccountIdAndGroupId(accountId, groupId);
    }

    @Transactional
    public AccountGroup updateRoleInGroup(Integer accountId, Integer groupId, String roleInGroup) {
        AccountGroup ag = accountGroupRepository.findByAccountIdAndGroupId(accountId, groupId)
                .orElseThrow(() -> new RuntimeException("Account not in this group"));
        ag.setRoleInGroup(roleInGroup);
        return accountGroupRepository.save(ag);
    }
}