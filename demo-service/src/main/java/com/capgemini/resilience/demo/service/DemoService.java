package com.capgemini.resilience.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    private final TravelServiceProxy travelServiceProxy;

    @Autowired
    public DemoService(TravelServiceProxy travelServiceProxy) {
        this.travelServiceProxy = travelServiceProxy;
    }


    public void executeDemoIteration() {
        travelServiceProxy.getTravels();
    }

}
