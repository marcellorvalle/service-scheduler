package com.marcellorvalle.scheduler.service.professional;

import com.marcellorvalle.scheduler.entity.Job;
import com.marcellorvalle.scheduler.entity.Professional;
import com.marcellorvalle.scheduler.service.job.JobService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Facade for services related to professionals
 */
@Service
@AllArgsConstructor
public class ProfessionalService {
    final ProfessionalCrudImpl professionalCrud;
    final JobService jobService;

    public ProfessionalCrud crud() {
        return professionalCrud;
    }

    /**
     * Associate the Professional with the given Job
     */
    public Professional addJob(Professional professional, Job job) {
        return professionalCrud.addJob(professional, job);
    }

    public List<Job> getJobsProvidedBy(Professional professional) {
        return jobService.crud().findByProfessional(professional);
    }
}
