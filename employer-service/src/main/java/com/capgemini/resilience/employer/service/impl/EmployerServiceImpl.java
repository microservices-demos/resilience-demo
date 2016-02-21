package com.capgemini.resilience.employer.service.impl;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.capgemini.resilience.employer.error.ErrorSimulation;
import com.capgemini.resilience.employer.model.Employer;
import com.capgemini.resilience.employer.repository.EmployerRepository;
import com.capgemini.resilience.employer.service.EmployerSearchCriteria;
import com.capgemini.resilience.employer.service.EmployerService;

/**
 * Created by kso on 15.02.16.
 */
@Transactional
@Named
@ErrorSimulation
public class EmployerServiceImpl implements EmployerService {

    @Inject
    private EmployerRepository repository;

    @Override
    public List<Employer> findAll() {
        return repository.findAll();
    }

    @Override
    public Employer read(Long id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public void saveOrUpdate(Employer employer) {
        repository.save(employer);
    }

    @Override
    public List<Employer> search(EmployerSearchCriteria searchCriteria) {
        return repository.search(searchCriteria);
    }
}
