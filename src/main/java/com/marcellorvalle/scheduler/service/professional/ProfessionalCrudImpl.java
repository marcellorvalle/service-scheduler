package com.marcellorvalle.scheduler.service.professional;

import com.marcellorvalle.scheduler.entity.Job;
import com.marcellorvalle.scheduler.entity.Professional;
import com.marcellorvalle.scheduler.repository.ProfessionalRepository;
import com.marcellorvalle.scheduler.util.exception.http.ResourceNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Service
class ProfessionalCrudImpl implements ProfessionalCrud {
    final ProfessionalRepository repository;

    @Override
    public Professional save(Professional pro) {
        return repository.save(pro);
    }

    @Override
    public void delete(Professional pro) {
        repository.delete(pro);
    }

    @Override
    public Professional findById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.doThrow("Nenhum profissional encontrado!"));
    }

    /**
     * Set the professional as a Job provider;
     */
    Professional addJob(Professional professional, Job job) {
        final List<Job> jobs = Objects.requireNonNullElse(
                professional.getJobs(), new ArrayList<>()
        );

        if (!jobs.contains(job)) {
            jobs.add(job);
            professional.setJobs(jobs);
            repository.save(professional);
        }

        return professional;
    }
}
