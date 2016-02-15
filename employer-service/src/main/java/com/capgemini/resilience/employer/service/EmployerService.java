package com.capgemini.resilience.employer.service;

import java.util.List;

import com.capgemini.resilience.employer.model.Employer;

/**
 * Created by kso on 15.02.16.
 */
public interface EmployerService {

    List<Employer> findAll();

    Employer read(Long id);

    void delete(Long id);

    void saveOrUpdate(Employer employer);

    List<Employer> search(EmployerSearchCriteria searchCriteria);
}
