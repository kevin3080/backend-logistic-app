package com.logisticapp.backend_logistic_app.domain.port.in;

import com.logisticapp.backend_logistic_app.domain.model.LandShipment;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GetLandShipmentUseCase {
    Optional<LandShipment> getShipmentById(UUID id);

    List<LandShipment> getAllShipments();
}
