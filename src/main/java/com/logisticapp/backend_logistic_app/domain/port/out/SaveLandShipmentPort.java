package com.logisticapp.backend_logistic_app.domain.port.out;

import com.logisticapp.backend_logistic_app.domain.model.LandShipment;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SaveLandShipmentPort {
    LandShipment save(LandShipment shipment);

    Optional<LandShipment> findById(UUID id);

    List<LandShipment> findAll();

    void deleteById(UUID id);

    boolean existsByGuideNumber(String guideNumber);
}
