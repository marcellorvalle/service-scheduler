package com.marcellorvalle.scheduler.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Date;

@Data
@Entity
public class Schedule {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idProfessional")
    private Professional professional;
    @NotNull
    private DayOfWeek dayOfWeek;
    @NotNull
    private LocalTime start;
    @NotNull
    private LocalTime end;
    @NotNull
    private Date createdAt;
}
