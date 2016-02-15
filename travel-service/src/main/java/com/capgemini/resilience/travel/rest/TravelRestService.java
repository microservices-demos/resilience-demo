package com.capgemini.resilience.travel.rest;

import javax.inject.Inject;

import com.capgemini.resilience.travel.service.TravelService;

import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kso on 16.02.16.
 */
@RestController
public class TravelRestService {

    @Inject
    private TravelService service;
}
