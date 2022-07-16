package com.rafaelsantos.students.services;

import com.rafaelsantos.students.domain.Student;

import java.util.List;

public interface StudentService {
    Student findById(Integer id);
    List<Student> findAll();
}
