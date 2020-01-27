package com.marcellorvalle.scheduler.repository;


import com.marcellorvalle.scheduler.entity.Job;
import com.marcellorvalle.scheduler.entity.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findAllByProviders_IdProfessional(long id);

    @Query("select j.providers from Job j where j.idJob = :id")
    List<Professional> findAllProfessionalsByJob(@Param("id") long id);
}
