package com.marcellorvalle.scheduler.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Schedule implements Comparable<Schedule> {
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

    @Override
    public int compareTo(Schedule that) {
        return this.start.compareTo(that.start);
    }

    public Long getIdProfessional() {
        return professional.getId();
    }
}
