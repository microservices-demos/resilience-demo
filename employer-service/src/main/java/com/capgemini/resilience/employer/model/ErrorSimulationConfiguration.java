package com.capgemini.resilience.employer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ErrorSimulationConfiguration {

    @Id
    @GeneratedValue
    private long id;

    private int errorProbability;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getErrorProbability() {
        return errorProbability;
    }

    public void setErrorProbability(int errorProbability) {
        this.errorProbability = errorProbability;
    }
}
