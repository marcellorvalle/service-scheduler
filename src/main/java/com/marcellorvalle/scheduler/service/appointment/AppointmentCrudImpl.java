package com.marcellorvalle.scheduler.service.appointment;


import com.marcellorvalle.scheduler.entity.Appointment;
import com.marcellorvalle.scheduler.repository.AppointmentRepository;
import com.marcellorvalle.scheduler.util.exception.http.ResourceNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Service
class AppointmentCrudImpl implements AppointmentCrud {
    private final AppointmentRepository repository;

    @Override
    public Appointment save(Appointment appointment) {
        return repository.save(appointment);
    }

    @Override
    public Appointment findById(long id) {
        var message = "Não foi possível localizar o agendamento!";

        return repository.findById(id).orElseThrow(
                () -> ResourceNotFoundException.doThrow(message)
        );
    }
}
