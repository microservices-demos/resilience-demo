package com.capgemini.resilience.travel.service;

import com.capgemini.resilience.travel.rest.EmployerTO;

/**
 * Created by kso on 18.02.16.
 */
public interface EmployerProxy {

    EmployerTO getEmployer(int number);
}
