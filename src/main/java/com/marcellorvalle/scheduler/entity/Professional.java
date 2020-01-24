package com.marcellorvalle.scheduler.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Professional extends Person {
    @OneToMany(fetch = FetchType.LAZY)
    private List<ScheduleItem> scheduleItems;
}
