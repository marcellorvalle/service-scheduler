package com.marcellorvalle.scheduler.service.schedule;

import com.marcellorvalle.scheduler.entity.ScheduleItem;
import com.marcellorvalle.scheduler.util.exception.http.BadRequestException;

public class ValidateSchedule {
    void execute(ScheduleItem scheduleItem) {
        validateInterval(scheduleItem);
    }

    private void validateInterval(ScheduleItem scheduleItem) {
        if (scheduleItem.getEnd().compareTo(scheduleItem.getStart()) < 0) {
            BadRequestException.doThrow(
                    "O horário final não deve ser menor que o inicial!"
            );
        }
    }
}
