package com.capgemini.resilience.travel.rest;

import com.capgemini.resilience.travel.model.Status;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by ksobkowi on 16.02.2016.
 */
public class TravelTO implements Serializable {

    private Long id;
    private int number;
    private String description;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate startDate;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate endDate;
    private Status status;
    private CostCenterTO costCenter;
    private EmployerTO employer;

    public TravelTO(Long id, int number, String description, LocalDate startDate, LocalDate endDate, Status status, CostCenterTO costCenter, EmployerTO employer) {
        this.id = id;
        this.number = number;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.costCenter = costCenter;
        this.employer = employer;
    }

    public TravelTO(int number, String description, LocalDate startDate, LocalDate endDate, Status status, CostCenterTO costCenter, EmployerTO employer) {
        this.number = number;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.costCenter = costCenter;
        this.employer = employer;
    }

    public TravelTO() {
    }

    public Long getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Status getStatus() {
        return status;
    }

    public CostCenterTO getCostCenter() {
        return costCenter;
    }

    public EmployerTO getEmployer() {
        return employer;
    }
}
