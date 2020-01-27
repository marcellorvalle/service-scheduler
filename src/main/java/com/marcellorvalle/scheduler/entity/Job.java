package com.marcellorvalle.scheduler.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_service")
    private Long idJob;

    @NotNull
    private String name;

    private String description;

    private Integer duration;

    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "jobs"
    )
    private List<Professional> providers;
}
