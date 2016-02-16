package com.capgemini.resilience.travel.rest;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import com.capgemini.resilience.travel.model.Travel;
import com.capgemini.resilience.travel.service.TravelService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kso on 16.02.16.
 */
@RestController
public class TravelRestService {

    @Inject
    private TravelService service;

    @RequestMapping(value = "/travel/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON})
    @ResponseBody
    public TravelTO get(@PathVariable("id") Long id) {
        Travel travel = service.read(id);
        return new TravelTO(
                travel.getId(),
                travel.getNumber(),
                travel.getDescription(),
                travel.getStartDate(),
                travel.getEndDate(),
                travel.getStatus(),
                travel.getCostCenterNumber(),
                travel.getEmployerNumber());
    }

    @RequestMapping(value = "/travel/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON})
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        this.service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/travel", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON})
    @ResponseBody
    public ResponseEntity<String> saveOrUpdate(@RequestBody TravelTO travel) {
        this.service.saveOrUpdate(new Travel(
                travel.getId(),
                travel.getNumber(),
                travel.getDescription(),
                travel.getStartDate(),
                travel.getEndDate(),
                travel.getStatus(),
                travel.getCostCenterNumber(),
                travel.getEmployerNumber()));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
