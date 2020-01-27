package com.marcellorvalle.scheduler.service.client;

import com.marcellorvalle.scheduler.entity.Client;

public interface ClientCrud {
    Client save(Client client);

    void delete(Client client);

    Client findById(long id);
}
