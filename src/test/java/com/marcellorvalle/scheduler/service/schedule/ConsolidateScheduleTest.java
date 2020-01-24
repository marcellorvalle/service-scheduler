package com.marcellorvalle.scheduler.service.schedule;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;


public class ConsolidateScheduleTest {
    private final ConsolidateSchedule consolidadeSchedule;

    public ConsolidateScheduleTest() {
        consolidadeSchedule = new ConsolidateSchedule();
    }

    @Test
    public void testConsolidation() {
        var input = Arrays.asList(
                ScheduleBuilder.fromInterval("13:00", "14:30"),
                ScheduleBuilder.fromInterval("17:30", "19:00"),
                ScheduleBuilder.fromInterval("09:00", "12:00"),
                ScheduleBuilder.fromInterval("08:00", "10:00"),
                ScheduleBuilder.fromInterval("14:30", "16:00"),
                ScheduleBuilder.fromInterval("16:15", "17:30")
        );

        var expected = Arrays.asList(
                ScheduleBuilder.fromInterval("08:00", "12:00"),
                ScheduleBuilder.fromInterval("13:00", "16:00"),
                ScheduleBuilder.fromInterval("16:15", "19:00")
        );


        assertArrayEquals(
                expected.toArray(),
                consolidadeSchedule.consolidate(input).toArray()
        );
    }

    @Test
    public void testConsolidationEmptyList() {
        assertArrayEquals(
                Collections.emptyList().toArray(),
                consolidadeSchedule.consolidate(Collections.emptyList()).toArray()
        );
    }
}
