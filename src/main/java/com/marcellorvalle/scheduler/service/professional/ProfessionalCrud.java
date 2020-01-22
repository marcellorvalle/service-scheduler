package com.marcellorvalle.scheduler.service.professional;

import com.marcellorvalle.scheduler.entity.Professional;

public interface ProfessionalCrud {
    Professional save(Professional pro);

    void delete(Professional pro);

    Professional findById(long id);
}
