package com.capgemini.resilience.employer.service;

import com.capgemini.resilience.employer.model.ErrorSimulationConfiguration;

public interface ErrorSimulationConfigurationService {

    ErrorSimulationConfiguration updateErrorSimulationConfigurationService(ErrorSimulationConfiguration errorSimulationConfiguration);

    ErrorSimulationConfiguration getErrorSimulationConfiguration();
}
