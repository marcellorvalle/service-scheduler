package com.marcellorvalle.scheduler.service.appointment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AppointmentService {
    private final AppointmentCrudImpl crud;

    public AppointmentCrud crud() {
        return crud;
    }
}
