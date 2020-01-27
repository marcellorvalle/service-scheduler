package com.marcellorvalle.scheduler.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Client extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_client")
    private Long idClient;
}
