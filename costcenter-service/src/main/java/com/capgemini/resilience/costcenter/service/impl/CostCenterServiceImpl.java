package com.capgemini.resilience.costcenter.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.capgemini.resilience.costcenter.model.CostCenter;
import com.capgemini.resilience.costcenter.repository.CostCenterRepository;
import com.capgemini.resilience.costcenter.service.CostCenterSearchCriteria;
import com.capgemini.resilience.costcenter.service.CostCenterService;

/**
 * Created by kso on 15.02.16.
 */
@Transactional
@Named
public class CostCenterServiceImpl implements CostCenterService {

    @Inject
    private CostCenterRepository repository;

    @Override
    public List<CostCenter> findAll() {
        return repository.findAll();
    }

    @Override
    public CostCenter read(Long id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public void saveOrUpdate(CostCenter costCenter) {
        repository.save(costCenter);
    }

    @Override
    public List<CostCenter> search(CostCenterSearchCriteria searchCriteria) {
        return repository.search(searchCriteria);
    }
}
