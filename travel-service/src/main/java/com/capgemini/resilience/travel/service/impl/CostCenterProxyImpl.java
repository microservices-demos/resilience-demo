package com.capgemini.resilience.travel.service.impl;

import java.util.List;
import javax.inject.Named;

import com.capgemini.resilience.travel.rest.CostCenterTO;
import com.capgemini.resilience.travel.service.CostCenterProxy;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;

/**
 * Created by kso on 18.02.16.
 */
@Named
public class CostCenterProxyImpl implements CostCenterProxy {

    @Override
    @HystrixCommand(fallbackMethod = "getFallbackCostCenter")
    public CostCenterTO get(int number) {
        RestTemplate restTemplate = new RestTemplate();
        CostCenterTO[] costCenters = restTemplate.getForObject("http://localhost:8083/costcenter?number=" + number, CostCenterTO[].class);
        return costCenters.length > 0 ? costCenters[0] : null;
    }


    public CostCenterTO getFallbackCostCenter(int number) {
        return new CostCenterTO(number, "fallback");
    }
}
