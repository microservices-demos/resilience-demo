package com.capgemini.resilience.travel.service.impl;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.capgemini.resilience.travel.model.Status;
import com.capgemini.resilience.travel.model.Travel;
import com.capgemini.resilience.travel.repository.TravelRepository;
import com.capgemini.resilience.travel.service.TravelSearchCriteria;
import com.capgemini.resilience.travel.service.TravelService;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by kso on 16.02.16.
 */
@Transactional
@Named
public class TravelServiceImpl implements TravelService {

    @Inject
    private TravelRepository repository;

    @PostConstruct
    public void postConstruct() {
        Travel travel = new Travel(3001, "TravelTO 1", LocalDate.now(), LocalDate.now().plusDays(3), Status.pending, 1001, 2001);
        System.out.println(travel);
        repository.save(travel);
    }

    @Override
    public Travel read(Long id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public void saveOrUpdate(Travel travel) {
        repository.save(travel);
    }

    @Override
    public List<Travel> search(TravelSearchCriteria searchCriteria) {
        return repository.search(searchCriteria);
    }
}
