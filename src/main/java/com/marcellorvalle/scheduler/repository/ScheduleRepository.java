package com.marcellorvalle.scheduler.repository;

import com.marcellorvalle.scheduler.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.DayOfWeek;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("select s from Schedule s " +
            "where s.professional.id = :idProfessional " +
            "and s.dayOfWeek = :day " +
            "order by s.start")
    List<Schedule> findByProfessionalAndAndDayOfWeek(
            @Param("idProfessional") long idProfessional,
            @Param("day") DayOfWeek day
    );

    @Modifying
    @Query("delete from Schedule s " +
            "where s.professional.id = :idProfessional " +
            "and s.dayOfWeek = :day")
    void deleteEntireDay(
            @Param("idProfessional") long idProfessional,
            @Param("day") DayOfWeek day
    );

    List<Schedule> saveMe(List<Schedule> schedules);
}
