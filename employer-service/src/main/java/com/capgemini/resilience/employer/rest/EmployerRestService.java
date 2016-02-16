package com.capgemini.resilience.employer.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import com.capgemini.resilience.employer.model.Employer;
import com.capgemini.resilience.employer.service.EmployerSearchCriteria;
import com.capgemini.resilience.employer.service.EmployerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kso on 15.02.16.
 */
@RestController
public class EmployerRestService {

    @Inject
    private EmployerService service;

    @RequestMapping(value = "/employer/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON})
    @ResponseBody
    public Employer get(@PathVariable("id") Long id) {
        return service.read(id);
    }

    @RequestMapping(value = "/employer/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON})
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {

        this.service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/employer", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON})
    @ResponseBody
    public ResponseEntity<String> saveOrUpdate(@RequestBody Employer employer) {

        this.service.saveOrUpdate(employer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/employer", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON})
    @ResponseBody
    List<Employer> search(EmployerSearchCriteria searchCriteria) {
        return service.search(searchCriteria);
    }
}
