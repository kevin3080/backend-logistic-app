package com.logisticapp.backend_logistic_app.domain.port.in;

import com.logisticapp.backend_logistic_app.domain.model.Client;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GetClientUseCase {
    Optional<Client> getClientById(UUID id);

    List<Client> getAllClients();
}
