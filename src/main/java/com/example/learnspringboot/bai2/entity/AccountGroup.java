package com.example.learnspringboot.bai2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account_group")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @Column(name = "joined_at")
    private java.time.LocalDateTime joinedAt = java.time.LocalDateTime.now();

    @Column(name = "role_in_group", length = 50)
    private String roleInGroup;
}