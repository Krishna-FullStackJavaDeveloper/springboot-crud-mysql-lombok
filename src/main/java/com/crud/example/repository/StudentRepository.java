package com.crud.example.repository;

import com.crud.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    //Query method
    Optional<Student> findByEmail(String email);
}
