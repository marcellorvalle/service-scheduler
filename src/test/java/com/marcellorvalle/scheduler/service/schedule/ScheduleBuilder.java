package com.marcellorvalle.scheduler.service.schedule;

import com.marcellorvalle.scheduler.entity.Professional;
import com.marcellorvalle.scheduler.entity.ScheduleItem;

import java.time.LocalTime;

class ScheduleBuilder {
    static ScheduleItem fromInterval(String start, String end) {
        return ScheduleItem.builder()
                .start(LocalTime.parse(start))
                .end(LocalTime.parse(end))
                .professional(new Professional())
                .build();
    }
}
