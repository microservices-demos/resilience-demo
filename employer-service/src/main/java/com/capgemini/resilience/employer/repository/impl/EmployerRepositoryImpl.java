package com.capgemini.resilience.employer.repository.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.capgemini.resilience.employer.model.Employer;
import com.capgemini.resilience.employer.model.QEmployer;
import com.capgemini.resilience.employer.repository.EmployerSearchRepository;
import com.capgemini.resilience.employer.service.EmployerSearchCriteria;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Predicate;

/**
 * Created by kso on 15.02.16.
 */
public class EmployerRepositoryImpl implements EmployerSearchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Employer> search(EmployerSearchCriteria searchCriteria) {
        JPAQuery query = new JPAQuery(this.entityManager);
        QEmployer employer = QEmployer.employer;
        return query.from(employer).where(applySearchCriteriaToQuery(searchCriteria, employer)).list(employer);
    }

    private Predicate[] applySearchCriteriaToQuery(EmployerSearchCriteria searchCriteria, QEmployer employer) {

        List<Predicate> predicates = new ArrayList<>();
        if (searchCriteria.hasName()) {
            predicates.add(employer.name.containsIgnoreCase(searchCriteria.getName()));
        }
        if (searchCriteria.hasFirstName()) {
            predicates.add(employer.firstName.containsIgnoreCase(searchCriteria.getFirstName()));
        }
        if (searchCriteria.hasNumber()) {
            predicates.add(employer.number.eq(searchCriteria.getNumber()));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }
}
