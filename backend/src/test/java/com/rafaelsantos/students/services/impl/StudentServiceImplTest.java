package com.rafaelsantos.students.services.impl;

import com.rafaelsantos.students.domain.Student;
import com.rafaelsantos.students.dto.StudentDTO;
import com.rafaelsantos.students.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class StudentServiceImplTest {

    private static final int ID = 1;
    private static final String NAME = "Rafael";
    private static final String SURNAME = "Santos";
    private static final String EMAIL = "rafael@email.com";
    private static final String PHONE_NUMBER = "1111111111";
    @InjectMocks
    private StudentServiceImpl service;

    @Mock
    private StudentRepository repository;

    @Mock
    private ModelMapper mapper;

    private StudentDTO studentDTO;

    private Student student;

    private Optional<Student> optionalStudent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startStudent();
    }

    @Test
    void whenFindByIdThenReturnAStudentInstance() {
       when(repository.findById(anyInt())).thenReturn(optionalStudent);

        Student response = service.findById(ID);

        assertNotNull(response);
        assertEquals(Student.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(SURNAME, response.getSurname());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PHONE_NUMBER, response.getPhoneNumber());
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startStudent(){
        student = new Student(ID, NAME, SURNAME, EMAIL, PHONE_NUMBER);

        studentDTO = new StudentDTO(ID, NAME, SURNAME, EMAIL, PHONE_NUMBER);

        optionalStudent = Optional.of(new Student(ID, NAME, SURNAME, EMAIL, PHONE_NUMBER));
    }
}