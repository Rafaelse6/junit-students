package com.rafaelsantos.students.services.impl;

import com.rafaelsantos.students.domain.Student;
import com.rafaelsantos.students.repositories.StudentRepository;
import com.rafaelsantos.students.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository repository;

    @Override
    public Student findById(Integer id) {
        Optional<Student> obj = repository.findById(id);
        return obj.orElse(null);
    }
}
