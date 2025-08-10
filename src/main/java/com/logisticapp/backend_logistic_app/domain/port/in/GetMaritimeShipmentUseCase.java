package com.logisticapp.backend_logistic_app.domain.port.in;

import com.logisticapp.backend_logistic_app.domain.model.MaritimeShipment;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GetMaritimeShipmentUseCase {
    Optional<MaritimeShipment> getShipmentById(UUID id);

    List<MaritimeShipment> getAllShipments();
}
