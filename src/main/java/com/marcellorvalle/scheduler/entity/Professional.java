package com.marcellorvalle.scheduler.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Professional extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_professional")
    private Long idProfessional;

    @OneToMany(fetch = FetchType.LAZY)
    private List<ScheduleItem> scheduleItems;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ProfessionalService",
            joinColumns = @JoinColumn(name = "idProfessional"),
            inverseJoinColumns = @JoinColumn(name = "idJob")
    )
    List<Job> jobs;
}
