package com.marcellorvalle.scheduler.service.schedule;

import com.marcellorvalle.scheduler.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Add a new schedule performing validation and consolidation
 */
@Service
@RequiredArgsConstructor
public class AddNewSchedule {
    final ScheduleCrudImplementation crud;
    final ConsolidateSchedule consolidateSchedule;
    final ValidateSchedule validateSchedule;

    /**
     * @return The consolidated schedule relative to the DayOfWeek
     */
    @Transactional
    List<Schedule> execute(Schedule schedule) {
        validateSchedule.execute(schedule);

        var others = crud.findOtherSchedules(schedule);
        others.add(schedule);

        var consolidated  = consolidateSchedule.consolidate(others);

        crud.deleteEntireDay(
                schedule.getIdProfessional(), schedule.getDayOfWeek()
        );

        return crud.saveAll(consolidated);
    }
}
