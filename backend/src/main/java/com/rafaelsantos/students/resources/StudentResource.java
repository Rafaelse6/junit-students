package com.rafaelsantos.students.resources;

import com.rafaelsantos.students.dto.StudentDTO;
import com.rafaelsantos.students.services.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/student")
public class StudentResource {

    private static final String ID = "/{id}";
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private StudentService service;

    @GetMapping(value = ID)
    public ResponseEntity<StudentDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(service.findById(id), StudentDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> findAll(){
        return ResponseEntity.ok().body(service.findAll().stream().map(x -> mapper.map(x, StudentDTO.class))
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<StudentDTO> create(@RequestBody StudentDTO obj){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID)
                .buildAndExpand(service.create(obj)).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = ID)
    public ResponseEntity<StudentDTO> update(@PathVariable Integer id, @RequestBody StudentDTO obj){
        obj.setId(id);
        return ResponseEntity.ok().body(mapper.map(service.update(obj), StudentDTO.class));
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<StudentDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
