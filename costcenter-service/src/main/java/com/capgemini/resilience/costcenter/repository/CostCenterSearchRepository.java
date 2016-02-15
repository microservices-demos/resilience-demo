package com.capgemini.resilience.costcenter.repository;

import java.util.List;

import com.capgemini.resilience.costcenter.model.CostCenter;
import com.capgemini.resilience.costcenter.service.CostCenterSearchCriteria;

/**
 * Created by kso on 15.02.16.
 */
public interface CostCenterSearchRepository {
    List<CostCenter> search(CostCenterSearchCriteria searchCriteria);
}
