package com.rafaelsantos.students.resources;

import com.rafaelsantos.students.domain.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/student")
public class StudentResource {

    @GetMapping(value = "/{id}")
    public ResponseEntity<Student> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(new Student(1, "Rafael", "Santos", "rafael@email.com",
                "11111111"));
    }
}
