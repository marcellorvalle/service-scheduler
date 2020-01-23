package com.marcellorvalle.scheduler.service.schedule;

import com.marcellorvalle.scheduler.entity.Schedule;

public interface ScheduleCrud {
    Schedule save(Schedule schedule);

    Schedule findById(long id);

    void delete(Schedule schedule);
}
