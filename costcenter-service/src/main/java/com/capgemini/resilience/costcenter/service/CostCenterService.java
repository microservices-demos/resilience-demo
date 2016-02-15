package com.capgemini.resilience.costcenter.service;

import java.util.List;

import com.capgemini.resilience.costcenter.model.CostCenter;

/**
 * Created by kso on 15.02.16.
 */
public interface CostCenterService {

    List<CostCenter> findAll();

    CostCenter read(Long id);

    void delete(Long id);

    void saveOrUpdate(CostCenter costCenter);

    List<CostCenter> search(CostCenterSearchCriteria searchCriteria);
}
