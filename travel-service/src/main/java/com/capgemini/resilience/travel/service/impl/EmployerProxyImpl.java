package com.capgemini.resilience.travel.service.impl;

import javax.inject.Named;

import com.capgemini.resilience.travel.rest.CostCenterTO;
import com.capgemini.resilience.travel.rest.EmployerTO;
import com.capgemini.resilience.travel.service.EmployerProxy;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.web.client.RestTemplate;

/**
 * Created by kso on 18.02.16.
 */
@Named
public class EmployerProxyImpl implements EmployerProxy {

    @Override
    @HystrixCommand(fallbackMethod = "getFallbackEmployer")
    public EmployerTO get(int number) {
        RestTemplate restTemplate = new RestTemplate();
        EmployerTO[] employers = restTemplate.getForObject("http://localhost:8081/employer?number=" + number, EmployerTO[].class);
        return employers.length > 0 ? employers[0] : null;
    }

    public EmployerTO getFallbackEmployer(int number) {
        return new EmployerTO(number, "fallback", "");
    }
}
