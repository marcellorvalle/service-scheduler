package com.marcellorvalle.scheduler.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@Data
@MappedSuperclass
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_person")
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String phoneNumber1;
    private String phoneNumber2;
    private String email;
}
