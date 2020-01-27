package com.marcellorvalle.scheduler.service.appointment;

import com.marcellorvalle.scheduler.entity.Appointment;

public interface AppointmentCrud {
    Appointment save(Appointment appointment);

    Appointment findById(long id);
}
