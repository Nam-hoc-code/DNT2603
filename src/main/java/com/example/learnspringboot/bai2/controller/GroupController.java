package com.example.learnspringboot.bai2.controller;

import com.example.learnspringboot.bai2.entity.Group;
import com.example.learnspringboot.bai2.service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<Group> getAll() {
        return groupService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> getById(@PathVariable Integer id) {
        return groupService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/with-accounts")
    public ResponseEntity<Group> getByIdWithAccounts(@PathVariable Integer id) {
        return groupService.getByIdWithAccounts(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Group> create(@RequestBody Group group) {
        try {
            return ResponseEntity.ok(groupService.create(group));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Group> update(@PathVariable Integer id, @RequestBody Group group) {
        try {
            return ResponseEntity.ok(groupService.update(id, group));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (groupService.getById(id).isPresent()) {
            groupService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}