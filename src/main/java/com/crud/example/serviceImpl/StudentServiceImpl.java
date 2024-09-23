package com.crud.example.serviceImpl;

import com.crud.example.entity.Student;
import com.crud.example.repository.StudentRepository;
import com.crud.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository;

    @Autowired
    public void StudentService(StudentRepository studentrepo) {
        this.studentRepository = studentrepo;
    }
    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findById(int id) {
        return studentRepository.findById(id);
    }

    @Override
    public Optional<Student> findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Override
    public Student save(Student std) {
        return studentRepository.save(std);
    }

    @Override
    public void deleteById(int id) {
        studentRepository.deleteById(id);
    }
}
