package com.marcellorvalle.scheduler.service.schedule;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ScheduleService {
    private final ScheduleCrud scheduleCrud;

    public ScheduleCrud crud() {
        return scheduleCrud;
    }
}
