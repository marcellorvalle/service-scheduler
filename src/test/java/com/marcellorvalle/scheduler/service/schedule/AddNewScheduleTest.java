package com.marcellorvalle.scheduler.service.schedule;

import com.marcellorvalle.scheduler.TestConfiguration;
import com.marcellorvalle.scheduler.entity.Professional;
import com.marcellorvalle.scheduler.entity.ScheduleItem;
import com.marcellorvalle.scheduler.service.professional.ProfessionalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.util.Date;

@SpringBootTest
@Import(TestConfiguration.class)
public class AddNewScheduleTest {
    private final AddNewSchedule addNewSchedule;

    private final Professional professional;
    private static final DayOfWeek DAY = DayOfWeek.MONDAY;

    @Autowired
    public AddNewScheduleTest(
            ProfessionalService professionalService,
            AddNewSchedule addNewSchedule
    ) {
        this.addNewSchedule = addNewSchedule;
        professional = professionalService.crud().findById(1);
    }

    @Test
    @Transactional
    public void testNewScheduleAdded() {
        addNewSchedule.execute(buildItem("08:00", "10:00"));
        addNewSchedule.execute(buildItem("09:45", "12:30"));
        var schedule = addNewSchedule.execute(buildItem("14:00", "17:30"));

        assertEquals(professional, schedule.professional);
        assertEquals(DAY, schedule.day);
    }

    private ScheduleItem buildItem(String start, String end) {
        var item = ScheduleBuilder.fromInterval(start, end);
        item.setProfessional(professional);
        item.setDayOfWeek(DAY);
        item.setCreatedAt(new Date());

        return item;
    }

}
