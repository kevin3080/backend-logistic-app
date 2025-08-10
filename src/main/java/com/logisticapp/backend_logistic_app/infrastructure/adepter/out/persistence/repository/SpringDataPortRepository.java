package com.logisticapp.backend_logistic_app.infrastructure.adepter.out.persistence.repository;

import com.logisticapp.backend_logistic_app.domain.model.Port;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataPortRepository extends JpaRepository<Port, UUID> {}
