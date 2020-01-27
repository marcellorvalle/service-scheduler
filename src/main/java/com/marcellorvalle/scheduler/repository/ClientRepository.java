package com.marcellorvalle.scheduler.repository;

import com.marcellorvalle.scheduler.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
