package com.marcellorvalle.scheduler.service.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientService {
    private final ClientCrud crud;

    public ClientCrud crud() {
        return crud;
    }

}
