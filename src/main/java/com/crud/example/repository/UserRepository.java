package com.crud.example.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.example.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
