package com.desj.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Julien on 23.04.16.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByEmail(String email);
}