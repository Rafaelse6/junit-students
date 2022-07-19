package com.rafaelsantos.students.services.impl;

import com.rafaelsantos.students.domain.Student;
import com.rafaelsantos.students.dto.StudentDTO;
import com.rafaelsantos.students.repositories.StudentRepository;
import com.rafaelsantos.students.services.StudentService;
import com.rafaelsantos.students.services.exceptions.DataIntegrityViolationException;
import com.rafaelsantos.students.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private StudentRepository repository;

    @Override
    public Student findById(Integer id) {
        Optional<Student> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    public List<Student> findAll(){
        return repository.findAll();
    }

    @Override
    public Student create(StudentDTO obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj, Student.class));
    }

    private void findByEmail(StudentDTO obj){
        Optional<Student> student = repository.findByEmail(obj.getEmail());
        if(student.isPresent()){
            throw new DataIntegrityViolationException("E-mail already in use");
        }
    }
}
