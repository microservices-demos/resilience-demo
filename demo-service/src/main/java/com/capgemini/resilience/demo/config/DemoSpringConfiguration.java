package com.capgemini.resilience.demo.config;

import com.capgemini.resilience.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class DemoSpringConfiguration {

    @Autowired
    private DemoService demoService;

    @Scheduled(fixedRate = 1000)
    public void executeDemoIteration() {
        demoService.executeDemoIteration();
    }


}
