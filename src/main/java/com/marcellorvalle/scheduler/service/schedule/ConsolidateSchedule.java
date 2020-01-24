package com.marcellorvalle.scheduler.service.schedule;

import com.marcellorvalle.scheduler.entity.ScheduleItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
class ConsolidateSchedule {
    List<ScheduleItem> execute(List<ScheduleItem> items) {
        if (items.isEmpty()) {
            return Collections.emptyList();
        }

        var clone = new ArrayList<>(items);
        clone.sort(ScheduleItem::compareTo);

        return mergeOverlaps(clone);
    }

    private List<ScheduleItem> mergeOverlaps(List<ScheduleItem> orderedItems) {
        var merged = orderedItems.get(0);
        var daySchedule = new ArrayList<ScheduleItem>();

        for (var schedule: orderedItems) {
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

    private boolean overlaped(ScheduleItem item, ScheduleItem other) {
        return item.getStart().compareTo(other.getEnd()) <= 0
                && item.getEnd().compareTo(other.getStart()) >= 0;
    }

    private ScheduleItem join(ScheduleItem startItem, ScheduleItem endItem) {
        return startItem.toBuilder()
                .end(endItem.getEnd())
                .build();
    }
}
