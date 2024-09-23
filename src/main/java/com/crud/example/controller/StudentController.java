package com.crud.example.controller;

import com.crud.example.entity.Student;
import com.crud.example.exception.StudentNotFoundException;
import com.crud.example.service.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    public StudentService studentService;

    @GetMapping(value="/getAll")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }
    @GetMapping(value="/getStudents/{id}")
    public Student getStudentById(@PathVariable("id") @Min(1) int id) {
        Student std = studentService.findById(id)
                .orElseThrow(()->new StudentNotFoundException("Student with "+id+" is Not Found!"));
        return std;
    }
    @PostMapping(value="/create")
    public Student addStudent(@Valid @RequestBody Student std) {
        return studentService.save(std);
    }
    @PutMapping(value="/update/{id}")
    public Student updateStudent(@PathVariable("id") @Min(1) int id, @Valid @RequestBody Student newstd) {
        Student stdu = studentService.findById(id)
                .orElseThrow(()->new StudentNotFoundException("Student with "+id+" is Not Found!"));
        stdu.setFirstName(newstd.getFirstName());
        stdu.setLastName(newstd.getLastName());
        stdu.setEmail(newstd.getEmail());
        return studentService.save(stdu);
    }
    @DeleteMapping(value="/delete/{id}")
    public String deleteStudent(@PathVariable("id") @Min(1) int id) {
        Student std = studentService.findById(id)
                .orElseThrow(()->new StudentNotFoundException("Student with "+id+" is Not Found!"));
        studentService.deleteById(std.getId());
        return "Student with ID :"+id+" is deleted";
    }
}
