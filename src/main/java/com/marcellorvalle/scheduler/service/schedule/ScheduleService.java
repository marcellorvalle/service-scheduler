package com.marcellorvalle.scheduler.service.schedule;

import com.marcellorvalle.scheduler.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleService {
    private final ScheduleCrud scheduleCrud;
    private final AddNewSchedule addNewSchedule;

    public ScheduleCrud crud() {
        return scheduleCrud;
    }

    List<Schedule> addNewSchedule(Schedule schedule) {
        return addNewSchedule.execute(schedule);
    }
}
