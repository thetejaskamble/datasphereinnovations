package com.project.Testing.repository;

import com.project.Testing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    //This is User Repository
    User findByEmail(String email);
}