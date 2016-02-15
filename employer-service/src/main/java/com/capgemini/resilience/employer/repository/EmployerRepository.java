package com.capgemini.resilience.employer.repository;

import com.capgemini.resilience.employer.model.Employer;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kso on 15.02.16.
 */
public interface EmployerRepository extends JpaRepository<Employer, Long>, EmployerSearchRepository {
}
