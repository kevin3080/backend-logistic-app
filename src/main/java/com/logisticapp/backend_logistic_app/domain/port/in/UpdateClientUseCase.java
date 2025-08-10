package com.logisticapp.backend_logistic_app.domain.port.in;

import com.logisticapp.backend_logistic_app.domain.model.Client;
import java.util.UUID;

public interface UpdateClientUseCase {
    Client updateClient(UUID id, Client client);
}
