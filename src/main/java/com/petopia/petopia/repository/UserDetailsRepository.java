package com.petopia.petopia.repository;

import com.petopia.petopia.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    @Query("Select u from UserDetails u where email = :email and isDeleted = false and isActive = true")
    UserDetails findByUserEmail(String email);

    @Query("Select u from UserDetails u where id = :userId and isDeleted = false and isActive = true")
    UserDetails findByUserIdAndIsDeletedFalseAndIsActive(Long userId);
}