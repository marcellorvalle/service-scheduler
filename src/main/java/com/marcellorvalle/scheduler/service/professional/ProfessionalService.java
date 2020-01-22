package com.marcellorvalle.scheduler.service.professional;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Facade for services related to professionals
 */
@Service
@AllArgsConstructor
public class ProfessionalService {
    final ProfessionalCrud professionalCrud;

    public ProfessionalCrud crud() {
        return professionalCrud;
    }
}
