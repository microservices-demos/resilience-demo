package com.capgemini.resilience.employer.repository;

import java.util.List;

import com.capgemini.resilience.employer.model.Employer;
import com.capgemini.resilience.employer.service.EmployerSearchCriteria;

/**
 * Created by kso on 15.02.16.
 */
public interface EmployerSearchRepository {
    List<Employer> search(EmployerSearchCriteria searchCriteria);
}
