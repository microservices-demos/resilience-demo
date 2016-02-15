package com.capgemini.resilience.costcenter.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.capgemini.resilience.costcenter.model.CostCenter;
import com.capgemini.resilience.costcenter.model.QCostCenter;
import com.capgemini.resilience.costcenter.repository.CostCenterSearchRepository;
import com.capgemini.resilience.costcenter.service.CostCenterSearchCriteria;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Predicate;

/**
 * Created by kso on 15.02.16.
 */
public class CostCenterRepositoryImpl implements CostCenterSearchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CostCenter> search(CostCenterSearchCriteria searchCriteria) {
        JPAQuery query = new JPAQuery(this.entityManager);
        QCostCenter costCenter = QCostCenter.costCenter;
        return query.from(costCenter).where(applySearchCriteriaToQuery(searchCriteria, costCenter)).list(costCenter);
    }

    private Predicate[] applySearchCriteriaToQuery(CostCenterSearchCriteria searchCriteria, QCostCenter costCenter) {

        List<Predicate> predicates = new ArrayList<>();
        if (searchCriteria.hasName()) {
            predicates.add(costCenter.name.containsIgnoreCase(searchCriteria.getName()));
        }
        if (searchCriteria.hasNumber()) {
            predicates.add(costCenter.number.eq(searchCriteria.getNumber()));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }
}
