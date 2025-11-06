package com.petopia.petopia.repository;

import com.petopia.petopia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(" Select u from User u where username = :username or email = :username and isDeleted = false ")
    User findByUsername(String username);
}