package com.capgemini.resilience.travel.service;

import com.capgemini.resilience.travel.rest.CostCenterTO;

/**
 * Created by kso on 18.02.16.
 */
public interface CostCenterProxy {

    CostCenterTO get(int number);

}
