package com.example.learnspringboot.bai2.service;

import com.example.learnspringboot.bai2.entity.Group;
import com.example.learnspringboot.bai2.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    public Optional<Group> getById(Integer id) {
        return groupRepository.findById(id);
    }

    public Optional<Group> getByIdWithAccounts(Integer id) {
        return groupRepository.findByIdWithAccounts(id);
    }

    public Group create(Group group) {
        if (groupRepository.existsByName(group.getName())) {
            throw new RuntimeException("Group name already exists: " + group.getName());
        }
        return groupRepository.save(group);
    }

    public Group update(Integer id, Group updated) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found: " + id));
        group.setName(updated.getName());
        group.setDescription(updated.getDescription());
        return groupRepository.save(group);
    }

    public void delete(Integer id) {
        groupRepository.deleteById(id);
    }
}