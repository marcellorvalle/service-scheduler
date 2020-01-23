package com.marcellorvalle.scheduler.service.schedule;

import com.marcellorvalle.scheduler.entity.Schedule;
import com.marcellorvalle.scheduler.repository.ScheduleRepository;
import com.marcellorvalle.scheduler.util.exception.http.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class ScheduleCrudImplementation implements ScheduleCrud {
    private final ScheduleRepository repository;

    @Override
    public Schedule save(Schedule schedule) {
        return repository.save(schedule);
    }

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

}
