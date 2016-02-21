package com.capgemini.resilience.employer.repository;

import com.capgemini.resilience.employer.model.ErrorSimulationConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorSimulationConfigurationRepository extends JpaRepository<ErrorSimulationConfiguration, Long> {
}
