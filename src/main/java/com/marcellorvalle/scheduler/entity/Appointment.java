package com.marcellorvalle.scheduler.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_appointment")
    private Long idAppointment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY)
    private Professional professional;

    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    private Date start;

    private Date end;
}
