package com.marcellorvalle.scheduler.service.schedule;

import com.marcellorvalle.scheduler.entity.Schedule;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ScheduleValidation {
    private final ScheduleCrudImplementation scheduleCrud;

    void validateConflicts(Schedule schedule) {
        //Carregar todos as agendas do profisisonal no dia
        //verificar se existem horários sobrepostos
    }

}
