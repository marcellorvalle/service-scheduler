package com.marcellorvalle.scheduler.service.schedule;

import com.marcellorvalle.scheduler.entity.Professional;
import com.marcellorvalle.scheduler.entity.Schedule;

import java.time.DayOfWeek;
import java.util.List;

public interface ScheduleCrud {
    Schedule findById(long id);

    void delete(Schedule schedule);

    List<Schedule> findByProfessionalAndDay(Professional professional, DayOfWeek day);

    List<Schedule> findByProfessionalAndDay(long idProfessional, DayOfWeek day);
}
