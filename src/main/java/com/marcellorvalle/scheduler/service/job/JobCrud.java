package com.marcellorvalle.scheduler.service.job;

import com.marcellorvalle.scheduler.entity.Job;
import com.marcellorvalle.scheduler.entity.Professional;

import java.util.List;

public interface JobCrud {
    Job save(Job job);

    Job findById(long id);

    List<Job> findByProfessional(Professional professional);

    List<Job> findByProfessional(long idProfessional);

    List<Professional> findProfessionals(Job job);

    List<Professional> findProfessionals(long idJob);
}
