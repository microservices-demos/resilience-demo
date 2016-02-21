package com.capgemini.resilience.employer.error;

import com.capgemini.resilience.employer.service.ErrorSimulationService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ErrorAspect {

    private final ErrorSimulationService errorSimulationService;

    @Autowired
    public ErrorAspect(ErrorSimulationService errorSimulationService) {
        this.errorSimulationService = errorSimulationService;
    }

    @Before("publicMethodInsideAClassMarkedWithAtErrorSimulation()")
    public void tryToGenerateError() {
        errorSimulationService.generateErrorDependingOnErrorPossibility();
    }

    @Pointcut("within(@com.capgemini.resilience.employer.error.ErrorSimulation *)")
    public void beanAnnotatedWithErrorSimulation() {}

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {}

    @Pointcut("publicMethod() && beanAnnotatedWithErrorSimulation()")
    public void publicMethodInsideAClassMarkedWithAtErrorSimulation() {}

}
