package com.marcellorvalle.scheduler.service.schedule;

import com.marcellorvalle.scheduler.entity.ScheduleItem;
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
    List<ScheduleItem> execute(ScheduleItem scheduleItem) {
        validateSchedule.execute(scheduleItem);

        var others = crud.findOtherSchedules(scheduleItem);
        others.add(scheduleItem);

        var consolidated  = consolidateSchedule.execute(others);

        crud.deleteEntireDay(
                scheduleItem.getIdProfessional(), scheduleItem.getDayOfWeek()
        );

        return crud.saveAll(consolidated);
    }
}
