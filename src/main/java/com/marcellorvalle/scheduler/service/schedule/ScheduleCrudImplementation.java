package com.marcellorvalle.scheduler.service.schedule;

import com.marcellorvalle.scheduler.entity.Professional;
import com.marcellorvalle.scheduler.entity.Schedule;
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
    public Schedule findById(long id) {
        return repository.findById(id).orElseThrow(
                () -> ResourceNotFoundException.doThrow("Não foi possível encontrar esse item de agenda!")
        );
    }

    @Override
    public void delete(Schedule schedule) {
        repository.delete(schedule);
    }

    public List<Schedule> findByProfessionalAndDay(Professional professional, DayOfWeek day) {
        return findByProfessionalAndDay(
                Objects.requireNonNull(professional.getId()),
                day
        );
    }

    public List<Schedule> findByProfessionalAndDay(long idProfessional, DayOfWeek day) {
        return repository.findByProfessionalAndAndDayOfWeek(idProfessional, day);
    }

    Schedule save(Schedule schedule) {
        return repository.save(schedule);
    }

    List<Schedule> findOtherSchedules(Schedule schedule) {
        return findByProfessionalAndDay(
                Objects.requireNonNull(schedule.getProfessional()),
                schedule.getDayOfWeek()
        );
    }

    void deleteEntireDay(long idProfessional, DayOfWeek day) {
        repository.deleteEntireDay(idProfessional, day);
    }

    List<Schedule> saveAll(Iterable<Schedule> schedules) {
        return repository.saveAll(schedules);
    }
}
