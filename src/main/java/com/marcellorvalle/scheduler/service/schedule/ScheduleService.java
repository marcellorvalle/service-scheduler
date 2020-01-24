package com.marcellorvalle.scheduler.service.schedule;

import com.marcellorvalle.scheduler.entity.ScheduleItem;
import com.marcellorvalle.scheduler.struct.DaySchedule;
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

    DaySchedule addNewSchedule(ScheduleItem scheduleItem) {
        return addNewSchedule.execute(scheduleItem);
    }
}
