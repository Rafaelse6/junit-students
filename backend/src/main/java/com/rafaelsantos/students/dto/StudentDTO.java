package com.rafaelsantos.students.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
}
