package com.marcellorvalle.scheduler.entity;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@Data
@MappedSuperclass
public class Person {
    @NotNull
    private String name;
    @NotNull
    private String phoneNumber1;
    private String phoneNumber2;
    private String email;
}
