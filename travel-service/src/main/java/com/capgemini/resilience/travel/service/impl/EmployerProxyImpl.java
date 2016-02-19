package com.capgemini.resilience.travel.service.impl;

import javax.inject.Named;

import com.capgemini.resilience.travel.rest.CostCenterTO;
import com.capgemini.resilience.travel.rest.EmployerTO;
import com.capgemini.resilience.travel.service.EmployerProxy;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by kso on 18.02.16.
 */
@Named
public class EmployerProxyImpl implements EmployerProxy {

    @Value("${employer.service.url}")
    private String url;

    @Override
    @HystrixCommand(fallbackMethod = "getFallbackEmployer")
    public EmployerTO get(int number) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<EmployerTO>> exchange = restTemplate.exchange(
                url + "/employer?number=" + number,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<EmployerTO>>() {
                });

        if (!exchange.getBody().isEmpty())
            return exchange.getBody().iterator().next();
        else
            return null;
    }

    public EmployerTO getFallbackEmployer(int number) {
        return new EmployerTO(number, "fallback", "");
    }
}
