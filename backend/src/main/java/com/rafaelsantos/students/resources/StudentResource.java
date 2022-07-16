package com.rafaelsantos.students.resources;

import com.rafaelsantos.students.dto.StudentDTO;
import com.rafaelsantos.students.services.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/student")
public class StudentResource {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private StudentService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<StudentDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(service.findById(id), StudentDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> findAll(){
        return ResponseEntity.ok().body(service.findAll().stream().map(x -> mapper.map(x, StudentDTO.class)).collect(Collectors.toList()));
    }
}
