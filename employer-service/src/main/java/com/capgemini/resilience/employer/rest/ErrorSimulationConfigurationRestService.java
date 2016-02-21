package com.capgemini.resilience.employer.rest;

import com.capgemini.resilience.employer.model.ErrorSimulationConfiguration;
import com.capgemini.resilience.employer.service.ErrorSimulationConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorSimulationConfigurationRestService {

    private final ErrorSimulationConfigurationService errorSimulationConfigurationService;

    @Autowired
    public ErrorSimulationConfigurationRestService(ErrorSimulationConfigurationService errorSimulationConfigurationService) {
        this.errorSimulationConfigurationService = errorSimulationConfigurationService;
    }

    @RequestMapping(value= "/error-simulation-configuration", method = RequestMethod.POST)
    public ErrorSimulationConfiguration updateErrorSimulationConfiguration(@RequestBody ErrorSimulationConfiguration errorSimulationConfiguration) {
        return this.errorSimulationConfigurationService.updateErrorSimulationConfigurationService(errorSimulationConfiguration);
    }

}
