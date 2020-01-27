package com.marcellorvalle.scheduler.service.job;

import com.marcellorvalle.scheduler.entity.Job;
import com.marcellorvalle.scheduler.entity.Professional;
import com.marcellorvalle.scheduler.repository.JobRepository;
import com.marcellorvalle.scheduler.util.exception.http.ResourceNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Service
class JobCrudImpl implements JobCrud {
    private final JobRepository repository;

    @Override
    public Job save(Job job) {
        return repository.save(job);
    }

    @Override
    public Job findById(long id) {
        var message = "Não foi possível encontar o serviço!";

        return repository.findById(id).orElseThrow(
                () -> ResourceNotFoundException.doThrow(message)
        );
    }

    @Override
    public List<Job> findByProfessional(Professional professional) {
        return findByProfessional(professional.getIdProfessional());
    }

    @Override
    public List<Job> findByProfessional(long idProfessional) {
        return repository.findAllByProviders_IdProfessional(idProfessional);
    }

    @Override
    public List<Professional> findProfessionals(Job job) {
        return repository.findAllProfessionalsByJob(job.getIdJob());
    }

    @Override
    public List<Professional> findProfessionals(long idJob) {
        return repository.findAllProfessionalsByJob(idJob);
    }
}
