package com.example.learnspringboot.bai2.service;

import com.example.learnspringboot.bai2.entity.Group;

import java.util.List;
import java.util.Optional;

public interface IGroupService {
    List<Group> getAll();
    Optional<Group> getById(Integer id);
    Optional<Group> getByIdWithAccounts(Integer id);
    Group create(Group group);
    Group update(Integer id, Group updated);
    void delete(Integer id);
}