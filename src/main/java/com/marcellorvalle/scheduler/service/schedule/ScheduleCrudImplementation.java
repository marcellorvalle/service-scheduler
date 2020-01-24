package com.marcellorvalle.scheduler.service.schedule;

import com.marcellorvalle.scheduler.entity.Professional;
import com.marcellorvalle.scheduler.entity.ScheduleItem;
import com.marcellorvalle.scheduler.repository.ScheduleRepository;
import com.marcellorvalle.scheduler.util.exception.http.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
class ScheduleCrudImplementation implements ScheduleCrud {
    private final ScheduleRepository repository;

    @Override
    public ScheduleItem findById(long id) {
        return repository.findById(id).orElseThrow(
                () -> ResourceNotFoundException.doThrow("Não foi possível encontrar esse item de agenda!")
        );
    }

    @Override
    public void delete(ScheduleItem scheduleItem) {
        repository.delete(scheduleItem);
    }

    public List<ScheduleItem> findByProfessionalAndDay(Professional professional, DayOfWeek day) {
        return findByProfessionalAndDay(
                Objects.requireNonNull(professional.getId()),
                day
        );
    }

    public List<ScheduleItem> findByProfessionalAndDay(long idProfessional, DayOfWeek day) {
        return repository.findByProfessionalAndAndDayOfWeek(idProfessional, day);
    }

    ScheduleItem save(ScheduleItem scheduleItem) {
        return repository.save(scheduleItem);
    }

    List<ScheduleItem> findOtherSchedules(ScheduleItem scheduleItem) {
        return findByProfessionalAndDay(
                Objects.requireNonNull(scheduleItem.getProfessional()),
                scheduleItem.getDayOfWeek()
        );
    }

    void deleteEntireDay(long idProfessional, DayOfWeek day) {
        repository.deleteEntireDay(idProfessional, day);
    }

    List<ScheduleItem> saveAll(Iterable<ScheduleItem> schedules) {
        return repository.saveAll(schedules);
    }
}
