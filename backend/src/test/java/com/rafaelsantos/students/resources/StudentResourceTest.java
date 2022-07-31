package com.rafaelsantos.students.resources;

import com.rafaelsantos.students.domain.Student;
import com.rafaelsantos.students.dto.StudentDTO;
import com.rafaelsantos.students.services.impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentResourceTest {

    private static final int ID = 1;

    private static final int INDEX = 0;
    private static final String NAME = "Rafael";
    private static final String SURNAME = "Santos";
    private static final String EMAIL = "rafael@email.com";
    private static final String PHONE_NUMBER = "1111111111";

    @InjectMocks
    private StudentResource resource;

    @Mock
    private StudentServiceImpl service;

    @Mock
    private ModelMapper mapper;

    private Student student;

    private StudentDTO studentDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startStudent();
    }

    @Test
    void findById() {
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
    }
}