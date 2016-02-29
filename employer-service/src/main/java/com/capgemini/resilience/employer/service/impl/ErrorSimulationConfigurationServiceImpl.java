package com.capgemini.resilience.employer.service.impl;

import com.capgemini.resilience.employer.model.ErrorSimulationConfiguration;
import com.capgemini.resilience.employer.repository.ErrorSimulationConfigurationRepository;
import com.capgemini.resilience.employer.service.ErrorSimulationConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Transactional
@Service
public class ErrorSimulationConfigurationServiceImpl implements ErrorSimulationConfigurationService {

    private final ErrorSimulationConfigurationRepository errorSimulationConfigurationRepository;

    private static final String CACHE_NAME = "errorSimulationConfigurationCache";

    @Autowired
    public ErrorSimulationConfigurationServiceImpl(ErrorSimulationConfigurationRepository errorSimulationConfigurationRepository) {
        this.errorSimulationConfigurationRepository = errorSimulationConfigurationRepository;
    }

    @CacheEvict(value = CACHE_NAME, allEntries = true)
    @Override
    public ErrorSimulationConfiguration updateErrorSimulationConfigurationService(ErrorSimulationConfiguration errorSimulationConfiguration) {
        Assert.notNull(errorSimulationConfiguration, "errorSimulationConfiguration must be not null");
        validateErrorProbality(errorSimulationConfiguration.getErrorProbability());

        List<ErrorSimulationConfiguration> configurations = errorSimulationConfigurationRepository.findAll();
        final ErrorSimulationConfiguration configuration;
        if (configurations.size() == 1) {
            configuration = configurations.get(0);
        } else {
            configuration = new ErrorSimulationConfiguration();
            errorSimulationConfigurationRepository.save(configuration);
        }
        configuration.setErrorProbability(errorSimulationConfiguration.getErrorProbability());
        return configuration;
    }

    @Cacheable(CACHE_NAME)
    @Override
    public ErrorSimulationConfiguration getErrorSimulationConfiguration() {
        List<ErrorSimulationConfiguration> configurations = errorSimulationConfigurationRepository.findAll();
        final ErrorSimulationConfiguration configuration;
        if (configurations.size() == 1) {
            configuration = configurations.get(0);
        } else {
            configuration = getDefaultConfiguration();
        }
        return configuration;
    }

    private void validateErrorProbality(int errorProbability) {
        if (errorProbability < 0 || errorProbability > 100) {
            throw new IllegalArgumentException("Illegal error probability - expected value in range <0, 100>.");
        }
    }

    private ErrorSimulationConfiguration getDefaultConfiguration() {
        ErrorSimulationConfiguration configuration = new ErrorSimulationConfiguration();
        configuration.setErrorProbability(0);
        return configuration;
    }
}
