package com.capgemini.resilience.costcenter.repository;

import com.capgemini.resilience.costcenter.model.CostCenter;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kso on 15.02.16.
 */
public interface CostCenterRepository extends JpaRepository<CostCenter, Long>, CostCenterSearchRepository {
}
