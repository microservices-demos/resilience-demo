package com.capgemini.resilience.travel.service;

import com.capgemini.resilience.travel.rest.EmployerTO;
import rx.Observable;

/**
 * Created by kso on 18.02.16.
 */
public interface EmployerProxy {

    EmployerTO getEmployer(int number);

    Observable<EmployerTO> getEmployerObservable(int number);
}
