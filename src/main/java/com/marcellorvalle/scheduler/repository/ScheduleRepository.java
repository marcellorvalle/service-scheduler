package com.marcellorvalle.scheduler.repository;

import com.marcellorvalle.scheduler.entity.ScheduleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<ScheduleItem, Long> {

    @Query("select s from ScheduleItem s " +
            "where s.professional.id = :idProfessional " +
            "and s.dayOfWeek = :day " +
            "order by s.start")
    List<ScheduleItem> findByProfessionalAndAndDayOfWeek(
            @Param("idProfessional") long idProfessional,
            @Param("day") DayOfWeek day
    );

    @Modifying
    @Transactional
    @Query("delete from ScheduleItem s " +
            "where s.professional.id = :idProfessional " +
            "and s.dayOfWeek = :day")
    void deleteEntireDay(
            @Param("idProfessional") long idProfessional,
            @Param("day") DayOfWeek day
    );
}
