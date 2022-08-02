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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

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
    void whenFindByIdThenReturnSuccess() {
        when(service.findById(anyInt())).thenReturn(student);
        when(mapper.map(any(), any())).thenReturn(studentDTO);

        ResponseEntity<StudentDTO> response = resource.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StudentDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(SURNAME, response.getBody().getSurname());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(PHONE_NUMBER, response.getBody().getPhoneNumber());

    }

    @Test
    void whenFindAllThenReturnAListOfStudentDTO() {
        when(service.findAll()).thenReturn(List.of(student));
        when(mapper.map(any(),any())).thenReturn(studentDTO);

        ResponseEntity<List<StudentDTO>> response = resource.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(StudentDTO.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NAME, response.getBody().get(INDEX).getName());
        assertEquals(SURNAME, response.getBody().get(INDEX).getSurname());
        assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
        assertEquals(PHONE_NUMBER, response.getBody().get(INDEX).getPhoneNumber());

    }

    @Test
    void whenCreateThenReturnCreated() {
        when(service.create(any())).thenReturn(student);

        ResponseEntity<StudentDTO> response = resource.create(studentDTO);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));
    }

    @Test
    void whenUpdateThenReturnSuccess() {

        when(service.update(studentDTO)).thenReturn(student);
        when(mapper.map(any(), any())).thenReturn(studentDTO);

        ResponseEntity<StudentDTO> response = resource.update(ID, studentDTO);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StudentDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(SURNAME, response.getBody().getSurname());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(PHONE_NUMBER, response.getBody().getPhoneNumber());
    }

    @Test
    void delete() {
    }

    private void startStudent(){
        student = new Student(ID, NAME, SURNAME, EMAIL, PHONE_NUMBER);

        studentDTO = new StudentDTO(ID, NAME, SURNAME, EMAIL, PHONE_NUMBER);
    }
}