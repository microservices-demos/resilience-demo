package com.capgemini.resilience.travel.service;

import java.util.List;

import com.capgemini.resilience.travel.model.Travel;

/**
 * Created by kso on 16.02.16.
 */
public interface TravelService {
    Travel read(Long id);

    void delete(Long id);

    void saveOrUpdate(Travel travel);

    List<Travel> search(TravelSearchCriteria searchCriteria);
}
