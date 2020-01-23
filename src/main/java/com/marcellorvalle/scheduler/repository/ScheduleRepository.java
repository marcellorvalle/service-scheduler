package com.marcellorvalle.scheduler.repository;

import com.marcellorvalle.scheduler.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
