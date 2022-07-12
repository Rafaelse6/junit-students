package com.rafaelsantos.students.services;

import com.rafaelsantos.students.domain.Student;

public interface StudentService {
    Student findById(Integer id);
}
