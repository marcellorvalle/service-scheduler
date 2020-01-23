package com.marcellorvalle.scheduler.service.schedule;

import com.marcellorvalle.scheduler.entity.Schedule;
import com.marcellorvalle.scheduler.util.exception.http.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor()
@Service
public class ScheduleConflictValidation {
    private final ScheduleCrudImplementation scheduleCrud;
    private Schedule schedule;

    void validate(Schedule schedule) {
        this.schedule = schedule;
        final List<Schedule> othersSameDay = scheduleCrud.findOtherSchedules(schedule);

        othersSameDay.forEach(this::checkConflicted);
    }

    private void checkConflicted(Schedule other) {
        if (conflicted(other)) {
            BadRequestException.doThrow("O horário do item de agenda entra em conflito com outro item existente!");
        }
    }

    private boolean conflicted(Schedule other) {
        if (same(schedule, other)) {
            return false;
        }

        return schedule.getStart().compareTo(other.getEnd()) < 0
                && schedule.getEnd().compareTo(other.getStart()) > 0;
    }

    private boolean same(Schedule schedule, Schedule other) {
        return Objects.nonNull(schedule.getId()) &&
                Objects.equals(schedule.getId(), other.getId());
    }

}
