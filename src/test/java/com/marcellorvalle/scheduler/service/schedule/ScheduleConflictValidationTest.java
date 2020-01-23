package com.marcellorvalle.scheduler.service.schedule;


import com.marcellorvalle.scheduler.entity.Professional;
import com.marcellorvalle.scheduler.entity.Schedule;
import com.marcellorvalle.scheduler.util.exception.http.BadRequestException;
import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ScheduleConflictValidationTest {
    private final ScheduleConflictValidation service;

    public ScheduleConflictValidationTest() {
        final ScheduleCrudImplementation crud = mock(ScheduleCrudImplementation.class);

        when(crud.findOtherSchedules(any()))
                .thenReturn(
                        Collections.singletonList(fromInterval("13:00", "19:00"))
                );

        service = new ScheduleConflictValidation(crud);
    }

    @Test
    public void testNoConflict() {
        final Schedule justBefore = fromInterval("10:00", "13:00");
        service.validate(justBefore);

        final Schedule justAfter = fromInterval("19:00", "22:00");
        service.validate(justAfter);
    }

    @Test
    public void testConflictAtStart() {
        final Schedule justBefore = fromInterval("10:00", "13:01");

        assertThrows(
                BadRequestException.class,
                () -> service.validate(justBefore)
        );
    }

    @Test
    public void testConflictAtEnd() {
        final Schedule justAfter = fromInterval("18:59", "22:00");

        assertThrows(
                BadRequestException.class,
                () -> service.validate(justAfter)
        );
    }

    @Test
    public void testConflictSame() {
        final Schedule conflicted = fromInterval("13:00", "19:00");

        assertThrows(
                BadRequestException.class,
                () -> service.validate(conflicted)
        );
    }

    @Test
    public void testConflictAround() {
        final Schedule conflicted = fromInterval("12:59", "19:01");

        assertThrows(
                BadRequestException.class,
                () -> service.validate(conflicted)
        );
    }

    @Test
    public void testConflictInside() {
        final Schedule conflicted = fromInterval("13:01", "16:59");

        assertThrows(
                BadRequestException.class,
                () -> service.validate(conflicted)
        );
    }

    private Schedule fromInterval(String start, String end) {
        return Schedule.builder()
                .start(LocalTime.parse(start))
                .end(LocalTime.parse(end))
                .professional(new Professional())
                .build();
    }
}
