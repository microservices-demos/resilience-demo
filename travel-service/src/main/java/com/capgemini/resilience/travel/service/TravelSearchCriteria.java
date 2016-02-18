package com.capgemini.resilience.travel.service;

import java.io.Serializable;
import java.time.LocalDate;

import com.capgemini.resilience.travel.model.Status;

import org.springframework.util.StringUtils;

/**
 * Created by kso on 18.02.16.
 */
public class TravelSearchCriteria implements Serializable {
    private Integer number;
    private String description;
    private LocalDate date;
    private Status status;
    private Integer costCenterNumber;
    private Integer employerNumber;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getCostCenterNumber() {
        return costCenterNumber;
    }

    public void setCostCenterNumber(Integer costCenterNumber) {
        this.costCenterNumber = costCenterNumber;
    }

    public Integer getEmployerNumber() {
        return employerNumber;
    }

    public void setEmployerNumber(Integer employerNumber) {
        this.employerNumber = employerNumber;
    }

    public boolean hasNumber() {
        return number != null;
    }

    public boolean hasCostCenterNumber() {
        return costCenterNumber != null;
    }

    public boolean hasEmployerNumber() {
        return employerNumber != null;
    }

    public boolean hasDate() {
        return date != null;
    }

    public boolean hasStatus() {
        return status != null;
    }

    public boolean hasDescription() {
        return StringUtils.hasText(description);
    }

}
