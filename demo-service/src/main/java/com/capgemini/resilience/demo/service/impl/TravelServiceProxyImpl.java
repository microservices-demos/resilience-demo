package com.capgemini.resilience.demo.service.impl;

import com.capgemini.resilience.demo.service.TravelServiceProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class TravelServiceProxyImpl implements TravelServiceProxy {

    @Value("${travel.service.url}")
    private String url;

    @Override
    public List<Object> getTravels() {
        ResponseEntity<List<Object>> response = new RestTemplate().exchange(
                url + "/travel",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Object>>() {
                });
        return response.getBody();
    }
}
