package com.marcellorvalle.scheduler.repository;

import com.marcellorvalle.scheduler.entity.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalRepository
        extends JpaRepository<Professional, Long>, JpaSpecificationExecutor<Professional> {
}
