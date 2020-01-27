package com.marcellorvalle.scheduler.service.schedule;

import com.marcellorvalle.scheduler.entity.ScheduleItem;
import com.marcellorvalle.scheduler.struct.DaySchedule;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Add a new schedule performing validation and consolidation
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Service
public class AddNewSchedule {
    final ScheduleCrudImplementation crud;
    final ConsolidateSchedule consolidateSchedule;
    final ValidateSchedule validateSchedule;

    /**
     * @return The consolidated schedule relative to the DayOfWeek
     */
    @Transactional
    DaySchedule execute(ScheduleItem scheduleItem) {
        validateSchedule.execute(scheduleItem);

        var others = crud.findOtherSchedules(scheduleItem);
        others.add(scheduleItem);
        var consolidated = consolidateSchedule.execute(others);

        crud.deleteEntireDay(
                scheduleItem.getIdProfessional(), scheduleItem.getDayOfWeek()
        );

        return new DaySchedule(
                crud.saveAll(consolidated)
        );
    }
}
