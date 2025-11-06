package com.petopia.petopia.entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.management.relation.Role;

@Data
@Entity
@Table(name = "user_role_mapping")
public class UserRoleMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_login_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles role;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
}
