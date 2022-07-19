package com.rafaelsantos.students.services;

import com.rafaelsantos.students.domain.Student;
import com.rafaelsantos.students.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    Student findById(Integer id);
    List<Student> findAll();
    Student create(StudentDTO obj);
    Student update(StudentDTO obj);
    void delete(Integer id);
}
