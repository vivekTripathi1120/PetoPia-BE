package com.petopia.petopia.repository;

import com.petopia.petopia.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RolesRepository extends JpaRepository<Roles, Long> {

    @Query(" Select r FROM Roles r where id = :roleId ")
    Roles findByRoleId(Long roleId);
}