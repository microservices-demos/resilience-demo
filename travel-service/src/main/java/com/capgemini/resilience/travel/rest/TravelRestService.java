package com.capgemini.resilience.travel.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import com.capgemini.resilience.travel.model.Travel;
import com.capgemini.resilience.travel.service.CostCenterProxy;
import com.capgemini.resilience.travel.service.EmployerProxy;
import com.capgemini.resilience.travel.service.TravelSearchCriteria;
import com.capgemini.resilience.travel.service.TravelService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;

/**
 * Created by kso on 16.02.16.
 */
@RestController
public class TravelRestService {

    @Inject
    private TravelService service;
    @Inject
    private CostCenterProxy costCenterProxy;
    @Inject
    private EmployerProxy employerProxy;

    @HystrixCommand
    @RequestMapping(value = "/travel/{id}", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON})
    @ResponseBody
    public ResponseEntity<TravelTO> get(@PathVariable("id") Long id) {
        Travel travel = service.read(id);
        CostCenterTO costCenterTO = costCenterProxy.getCostCenter(travel.getCostCenterNumber());
        if (costCenterTO == null) {
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        }
        EmployerTO employerTO = employerProxy.getEmployer(travel.getEmployerNumber());
        if (employerTO == null) {
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        }

        TravelTO travelTO = new TravelTO(
                travel.getId(),
                travel.getNumber(),
                travel.getDescription(),
                travel.getStartDate(),
                travel.getEndDate(),
                travel.getStatus(),
                costCenterTO,
                employerTO);

        return new ResponseEntity<>(travelTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/travel", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON})
    @ResponseBody
    public List<TravelTO> search(TravelSearchCriteria searchCriteria) {
        List<Travel> travels = service.search(searchCriteria);

        return travels.stream().map(travel ->
                new TravelTO(
                        travel.getId(),
                        travel.getNumber(),
                        travel.getDescription(),
                        travel.getStartDate(),
                        travel.getEndDate(),
                        travel.getStatus(),
                        costCenterProxy.getCostCenter(travel.getCostCenterNumber()),
                        employerProxy.getEmployer(travel.getEmployerNumber()))
        ).collect(Collectors.toList());
    }

    @RequestMapping(value = "/travel-reactive", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON})
    @ResponseBody
    public DeferredResult<List<TravelTO>> searchReactive(TravelSearchCriteria searchCriteria) {

        List<Travel> travels = service.search(searchCriteria);
        Observable<Travel> travelsObservable = Observable.from(travels);

        Observable<CostCenterTO> costCenters = travelsObservable.flatMap(travel -> costCenterProxy.getCostCenterObservable(travel.getCostCenterNumber()));

        Observable<EmployerTO> employers = travelsObservable.flatMap(travel -> employerProxy.getEmployerObservable(travel.getEmployerNumber()));

        Observable<Pair<CostCenterTO, EmployerTO>> restResults = costCenters.zipWith(employers, Pair::of);

        Observable<TravelTO> travelTos = travelsObservable.zipWith(restResults, (travel, restResult) -> new TravelTO(
                        travel.getId(),
                        travel.getNumber(),
                        travel.getDescription(),
                        travel.getStartDate(),
                        travel.getEndDate(),
                        travel.getStatus(),
                        restResult.getKey(),
                        restResult.getRight())
        );

        Observable<ArrayList<TravelTO>> travelTosList = travelTos.collect(ArrayList::new, (list, travelTo) -> list.add(travelTo));

        DeferredResult<List<TravelTO>> deferred = new DeferredResult<>();
        travelTosList.subscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
    }

    @RequestMapping(value = "/travel/{id}", method = RequestMethod.DELETE, produces = {
            MediaType.APPLICATION_JSON})
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        this.service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @HystrixCommand
    @RequestMapping(value = "/travel", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON})
    @ResponseBody
    public ResponseEntity<String> saveOrUpdate(@RequestBody TravelTO travel) {
        CostCenterTO costCenterTO = costCenterProxy.getCostCenter(travel.getCostCenter().getNumber());
        if (costCenterTO == null) {
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        }
        EmployerTO employerTO = employerProxy.getEmployer(travel.getEmployer().getNumber());
        if (employerTO == null) {
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        }

        this.service.saveOrUpdate(new Travel(
                travel.getId(),
                travel.getNumber(),
                travel.getDescription(),
                travel.getStartDate(),
                travel.getEndDate(),
                travel.getStatus(),
                travel.getCostCenter().getNumber(),
                travel.getEmployer().getNumber()));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
