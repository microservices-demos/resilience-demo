package com.capgemini.resilience.employer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmployerService {

  private final Random random = new Random();

  private static final double SUCCESS_PROBABILITY = 0.6;

  @HystrixCommand(fallbackMethod = "getFallbackEmployer")
  public String getEmployer() {

    double randomDouble = random.nextDouble();
    if (randomDouble <= SUCCESS_PROBABILITY) {
      return "Success employer";
    }

    throw new IllegalStateException("Test exception");
  }

  public String getFallbackEmployer() {
    return "Fallback employer";
  }

}