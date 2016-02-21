package com.capgemini.resilience.employer.service.impl;

import com.capgemini.resilience.employer.service.ErrorSimulationConfigurationService;
import com.capgemini.resilience.employer.service.ErrorSimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ErrorSimulationServiceImpl implements ErrorSimulationService {

    private static final int MAX_PROBABILITY = 100;

    private final Random random = new Random();

    private final ErrorSimulationConfigurationService errorSimulationConfigurationService;

    @Autowired
    public ErrorSimulationServiceImpl(ErrorSimulationConfigurationService errorSimulationConfigurationService) {
        this.errorSimulationConfigurationService = errorSimulationConfigurationService;
    }


    @Override
    public void generateErrorDependingOnErrorPossibility() {
        if (shouldGenerateError()) {
            generateError();
        }
    }

    private boolean shouldGenerateError() {
        int errorProbability = errorSimulationConfigurationService.getErrorSimulationConfiguration().getErrorProbability();
        int randomNumber = random.nextInt(MAX_PROBABILITY);
        return randomNumber <= errorProbability;
    }

    private void generateError() {
        throw new RuntimeException("Simulated error");
    }
}
