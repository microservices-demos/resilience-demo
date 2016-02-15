package com.capgemini.resilience.employer.rest;

import com.capgemini.resilience.employer.service.EmployerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployerRestService {

  private final EmployerService employerService;

  @Autowired
  public EmployerRestService(EmployerService employerService) {
    this.employerService = employerService;
  }

  @HystrixCommand
  @RequestMapping("/employer")
  public String getEmployer() {
    return this.employerService.getEmployer();
  }

}
