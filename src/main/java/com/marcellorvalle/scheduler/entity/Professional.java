package com.marcellorvalle.scheduler.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Professional extends Person {
    @ManyToOne(fetch = FetchType.LAZY)
    private ScheduleItem scheduleItem;
}
