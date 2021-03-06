package com.capgemini.resilience.travel.service.impl;

import com.capgemini.resilience.travel.rest.EmployerTO;
import com.capgemini.resilience.travel.service.EmployerProxy;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

import javax.inject.Named;
import java.util.List;

/**
 * Created by kso on 18.02.16.
 */
@Named
public class EmployerProxyImpl implements EmployerProxy {

    @Value("${employer.service.url}")
    private String url;
    
    @Autowired
    private RestTemplate restTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "getFallbackEmployer")
    public EmployerTO getEmployer(int number) {
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

    @HystrixCommand(fallbackMethod = "getFallbackEmployer",
            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5")})
    @Override
    public Observable<EmployerTO> getEmployerObservable(int number) {
        return Observable.create(subscriber -> {
            subscriber.onStart();
            subscriber.onNext(getEmployer(number));
            subscriber.onCompleted();
        });
    }

    public EmployerTO getFallbackEmployer(int number) {
        return new EmployerTO(number, "fallback", "");
    }
}
