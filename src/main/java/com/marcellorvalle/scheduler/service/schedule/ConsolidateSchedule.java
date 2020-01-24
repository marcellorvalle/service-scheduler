package com.marcellorvalle.scheduler.service.schedule;

import com.marcellorvalle.scheduler.entity.Schedule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
class ConsolidateSchedule {
    List<Schedule> consolidate(List<Schedule> schedules) {
        if (schedules.isEmpty()) {
            return Collections.emptyList();
        }

        var clone = new ArrayList<>(schedules);
        clone.sort(Schedule::compareTo);

        return mergeOverlaps(clone);
    }

    private List<Schedule> mergeOverlaps(List<Schedule> orderedSchedules) {
        var merged = orderedSchedules.get(0);
        var daySchedule = new ArrayList<Schedule>();

        for (var schedule: orderedSchedules) {
            if (overlaped(merged, schedule)) {
                merged = join(merged, schedule);
            } else {
                daySchedule.add(merged);
                merged = schedule;
            }
        }

        if (!daySchedule.contains(merged)) {
            daySchedule.add(merged);
        }

        return daySchedule;
    }

    private boolean overlaped(Schedule schedule, Schedule other) {
        return schedule.getStart().compareTo(other.getEnd()) <= 0
                && schedule.getEnd().compareTo(other.getStart()) >= 0;
    }

    private Schedule join(Schedule startSchedule, Schedule endSchedule) {
        return startSchedule.toBuilder()
                .end(endSchedule.getEnd())
                .build();
    }
}
