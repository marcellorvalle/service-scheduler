package com.marcellorvalle.scheduler.service.schedule;

import com.marcellorvalle.scheduler.entity.Schedule;
import com.marcellorvalle.scheduler.util.exception.http.BadRequestException;

public class ValidateSchedule {
    void execute(Schedule schedule) {
        validateInterval(schedule);
    }

    private void validateInterval(Schedule schedule) {
        if (schedule.getEnd().compareTo(schedule.getStart()) < 0) {
            BadRequestException.doThrow(
                    "O horário final não deve ser menor que o inicial!"
            );
        }
    }
}
