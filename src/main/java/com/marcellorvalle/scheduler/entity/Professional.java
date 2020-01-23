package com.marcellorvalle.scheduler.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Professional extends Person {
    @ManyToOne(fetch = FetchType.LAZY)
    private Schedule schedule;
}
