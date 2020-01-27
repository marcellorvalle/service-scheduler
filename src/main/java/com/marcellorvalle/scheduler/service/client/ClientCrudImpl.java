package com.marcellorvalle.scheduler.service.client;

import com.marcellorvalle.scheduler.entity.Client;
import com.marcellorvalle.scheduler.repository.ClientRepository;
import com.marcellorvalle.scheduler.util.exception.http.ResourceNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Service
class ClientCrudImpl implements ClientCrud {
    private final ClientRepository repository;

    @Override
    public Client save(Client client) {
        return repository.save(client);
    }

    @Override
    public void delete(Client client) {
        repository.delete(client);
    }

    @Override
    public Client findById(long id) {
        var message = "Não foi possível encontrar o cliente.";

        return repository.findById(id)
                .orElseThrow(() ->
                        ResourceNotFoundException.doThrow(message)
                );
    }

}
