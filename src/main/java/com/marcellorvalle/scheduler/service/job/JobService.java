package com.marcellorvalle.scheduler.service.job;

import com.marcellorvalle.scheduler.entity.Job;
import com.marcellorvalle.scheduler.entity.Professional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class JobService {
    private final JobCrudImpl jobCrud;

    public JobCrud crud() {
        return jobCrud;
    }

    public List<Professional> getProvidersOf(Job job) {
        return jobCrud.findProfessionals(job);
    }
}
