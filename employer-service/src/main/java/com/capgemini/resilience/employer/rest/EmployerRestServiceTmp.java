package com.capgemini.resilience.employer.rest;

import com.capgemini.resilience.employer.service.EmployerServiceTmp;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployerRestServiceTmp {

  private final EmployerServiceTmp employerService;

  @Autowired
  public EmployerRestServiceTmp(EmployerServiceTmp employerService) {
    this.employerService = employerService;
  }

  @HystrixCommand
  @RequestMapping("/employer")
  public String getEmployer() {
    return this.employerService.getEmployer();
  }

}
