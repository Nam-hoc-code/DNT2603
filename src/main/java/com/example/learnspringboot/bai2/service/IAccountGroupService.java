package com.example.learnspringboot.bai2.service;

import com.example.learnspringboot.bai2.entity.Account;
import com.example.learnspringboot.bai2.entity.AccountGroup;
import com.example.learnspringboot.bai2.entity.Group;

import java.util.List;

public interface IAccountGroupService {
    AccountGroup addAccountToGroup(Integer accountId, Integer groupId, String roleInGroup);
    List<Group> getGroupsByAccountId(Integer accountId);
    List<Account> getAccountsByGroupId(Integer groupId);
    List<AccountGroup> getAccountGroupsByAccountId(Integer accountId);
    List<AccountGroup> getAccountGroupsByGroupId(Integer groupId);
    void removeAccountFromGroup(Integer accountId, Integer groupId);
    AccountGroup updateRoleInGroup(Integer accountId, Integer groupId, String roleInGroup);
}