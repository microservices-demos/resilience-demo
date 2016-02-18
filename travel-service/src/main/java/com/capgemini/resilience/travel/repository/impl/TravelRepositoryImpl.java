package com.capgemini.resilience.travel.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.capgemini.resilience.travel.model.QTravel;
import com.capgemini.resilience.travel.model.Travel;
import com.capgemini.resilience.travel.repository.TravelSearchRepository;
import com.capgemini.resilience.travel.service.TravelSearchCriteria;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Predicate;

/**
 * Created by kso on 18.02.16.
 */
public class TravelRepositoryImpl implements TravelSearchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Travel> search(TravelSearchCriteria searchCriteria) {
        JPAQuery query = new JPAQuery(this.entityManager);
        QTravel travel = QTravel.travel;
        return query.from(travel).where(applySearchCriteriaToQuery(searchCriteria, travel)).list(travel);
    }

    private Predicate[] applySearchCriteriaToQuery(TravelSearchCriteria searchCriteria, QTravel travel) {

        List<Predicate> predicates = new ArrayList<>();
        if (searchCriteria.hasDescription()) {
            predicates.add(travel.description.containsIgnoreCase(searchCriteria.getDescription()));
        }
        if (searchCriteria.hasNumber()) {
            predicates.add(travel.number.eq(searchCriteria.getNumber()));
        }
        if (searchCriteria.hasEmployerNumber()) {
            predicates.add(travel.employerNumber.eq(searchCriteria.getEmployerNumber()));
        }
        if (searchCriteria.hasCostCenterNumber()) {
            predicates.add(travel.costCenterNumber.eq(searchCriteria.getCostCenterNumber()));
        }
        if (searchCriteria.hasStatus()) {
            predicates.add(travel.status.eq(searchCriteria.getStatus()));
        }
        if (searchCriteria.hasDate()) {
            predicates.add(travel.startDate.loe(searchCriteria.getDate())
                    .and(travel.endDate.goe(searchCriteria.getDate())));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }
}
