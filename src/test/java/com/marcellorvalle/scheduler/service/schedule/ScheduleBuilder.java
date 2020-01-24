package com.marcellorvalle.scheduler.service.schedule;

import com.marcellorvalle.scheduler.entity.Professional;
import com.marcellorvalle.scheduler.entity.Schedule;

import java.time.LocalTime;

class ScheduleBuilder {
    static Schedule fromInterval(String start, String end) {
        return Schedule.builder()
                .start(LocalTime.parse(start))
                .end(LocalTime.parse(end))
                .professional(new Professional())
                .build();
    }
}
