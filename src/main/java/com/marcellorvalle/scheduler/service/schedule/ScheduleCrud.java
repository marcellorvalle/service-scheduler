package com.marcellorvalle.scheduler.service.schedule;

import com.marcellorvalle.scheduler.entity.Professional;
import com.marcellorvalle.scheduler.entity.ScheduleItem;

import java.time.DayOfWeek;
import java.util.List;

public interface ScheduleCrud {
    ScheduleItem findById(long id);

    void delete(ScheduleItem scheduleItem);

    List<ScheduleItem> findByProfessionalAndDay(Professional professional, DayOfWeek day);

    List<ScheduleItem> findByProfessionalAndDay(long idProfessional, DayOfWeek day);
}
