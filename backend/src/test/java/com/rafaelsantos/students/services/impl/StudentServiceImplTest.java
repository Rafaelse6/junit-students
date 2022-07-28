package com.rafaelsantos.students.services.impl;

import com.rafaelsantos.students.domain.Student;
import com.rafaelsantos.students.dto.StudentDTO;
import com.rafaelsantos.students.repositories.StudentRepository;
import com.rafaelsantos.students.services.exceptions.DataIntegrityViolationException;
import com.rafaelsantos.students.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class StudentServiceImplTest {

    private static final int ID = 1;
    private static final String NAME = "Rafael";
    private static final String SURNAME = "Santos";
    private static final String EMAIL = "rafael@email.com";
    private static final String PHONE_NUMBER = "1111111111";
    private static final String OBJECT_NOT_FOUND = "Object not found";
    private static final int INDEX = 0;
    private static final String E_MAIL_ALREADY_IN_USE = "E-mail already in use";
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
    void whenFindByIdThenReturnAnObjectNotFoundException(){
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJECT_NOT_FOUND));
        try {
            service.findById(ID);
        }catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJECT_NOT_FOUND, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAListOfStudents() {
        when(repository.findAll()).thenReturn(List.of(student));

        List<Student> response = service.findAll();

        assertNotNull(response);
        assertEquals(1,response.size());
        assertEquals(Student.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(SURNAME, response.get(INDEX).getSurname());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PHONE_NUMBER, response.get(INDEX).getPhoneNumber());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(student);

        Student response = service.create(studentDTO);

        assertNotNull(response);
        assertEquals(Student.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(SURNAME, response.getSurname());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PHONE_NUMBER, response.getPhoneNumber());
    }

    @Test
    void whenCreateThenReturnADataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalStudent);

        try {
            optionalStudent.get().setId(2);
            service.create(studentDTO);
        }catch (Exception ex ){
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals(E_MAIL_ALREADY_IN_USE, ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(student);

        Student response = service.update(studentDTO);

        assertNotNull(response);
        assertEquals(Student.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(SURNAME, response.getSurname());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PHONE_NUMBER, response.getPhoneNumber());
    }

    @Test
    void whenUpdateThenReturnADataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalStudent);

        try {
            optionalStudent.get().setId(2);
            service.create(studentDTO);
        }catch (Exception ex ){
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals(E_MAIL_ALREADY_IN_USE, ex.getMessage());
        }
    }

    @Test
    void deleteWithSuccess() {
        when(repository.findById(anyInt())).thenReturn(optionalStudent);

        doNothing().when(repository).deleteById(anyInt());

        service.delete(ID);
        verify(repository, times(1)).deleteById(anyInt());
    }

    @Test
    void deleteWithObjectNotFoundException(){
       when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJECT_NOT_FOUND));

       try {
           service.delete(ID);
       }catch (Exception ex){
           assertEquals(ObjectNotFoundException.class, ex.getClass());
           assertEquals(OBJECT_NOT_FOUND, ex.getMessage());
       }
    }

    private void startStudent(){
        student = new Student(ID, NAME, SURNAME, EMAIL, PHONE_NUMBER);

        studentDTO = new StudentDTO(ID, NAME, SURNAME, EMAIL, PHONE_NUMBER);

        optionalStudent = Optional.of(new Student(ID, NAME, SURNAME, EMAIL, PHONE_NUMBER));
    }
}