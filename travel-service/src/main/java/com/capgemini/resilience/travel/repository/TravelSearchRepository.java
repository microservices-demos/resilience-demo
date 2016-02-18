package com.capgemini.resilience.travel.repository;

import java.util.List;

import com.capgemini.resilience.travel.model.Travel;
import com.capgemini.resilience.travel.service.TravelSearchCriteria;

/**
 * Created by kso on 18.02.16.
 */
public interface TravelSearchRepository {

    List<Travel> search(TravelSearchCriteria searchCriteria);
}
