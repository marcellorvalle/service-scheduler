package com.marcellorvalle.scheduler.service.professional;

import com.marcellorvalle.scheduler.entity.Professional;
import com.marcellorvalle.scheduler.repository.ProfessionalRepository;
import com.marcellorvalle.scheduler.util.exception.http.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
class ProfessionalCrudImpl implements ProfessionalCrud {
    final ProfessionalRepository professionalRepository;

    @Override
    public Professional save(Professional pro) {
        return professionalRepository.save(pro);
    }

    @Override
    public void delete(Professional pro) {
        professionalRepository.delete(pro);
    }

    @Override
    public Professional findById(long id) {
        return professionalRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.doThrow("Nenhum profissional encontrado!"));
    }
}
