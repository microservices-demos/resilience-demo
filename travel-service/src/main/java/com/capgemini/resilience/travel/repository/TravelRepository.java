package com.capgemini.resilience.travel.repository;

import com.capgemini.resilience.travel.model.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ksobkowi on 16.02.2016.
 */
public interface TravelRepository extends JpaRepository<Travel, Long> {
}
