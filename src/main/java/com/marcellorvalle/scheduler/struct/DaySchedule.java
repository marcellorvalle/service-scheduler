package com.marcellorvalle.scheduler.struct;

import com.marcellorvalle.scheduler.entity.Professional;
import com.marcellorvalle.scheduler.entity.ScheduleItem;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Objects;

/**
 * C-like immutable Struct to store all the schedules itens on a day
 */
public class DaySchedule {
    public final List<ScheduleItem> items;
    public final Professional professional;
    public final DayOfWeek day;

    public DaySchedule(List<ScheduleItem> items) {
        validate(items);

        this.items = items;
        this.professional = items.get(0).getProfessional();
        this.day = items.get(0).getDayOfWeek();
    }

    private void validate(List<ScheduleItem> items) {
        if (Objects.isNull(items) || items.isEmpty()) {
            throw new RuntimeException("Invalid item collection during the construction of DaySchedule");
        }
    }
}
