package com.rafaelsantos.students.config;

import com.rafaelsantos.students.domain.Student;
import com.rafaelsantos.students.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private StudentRepository repository;

    @Bean
    public void startDB(){
        Student s1 = new Student(null, "Rafael", "Santos", "rafael@email.com",
                "111111111");
        Student s2 = new Student(null, "Maria", "Santos", "maria@email.com",
                "111111111");

        repository.saveAll(List.of(s1,s2));
    }
}
