package com.capgemini.resilience.travel.service;

import com.capgemini.resilience.travel.rest.CostCenterTO;
import rx.Observable;

/**
 * Created by kso on 18.02.16.
 */
public interface CostCenterProxy {

    CostCenterTO getCostCenter(int number);

    Observable<CostCenterTO> getCostCenterObservable(int number);

}
