package com.capgemini.resilience.travel.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by ksobkowi on 16.02.2016.
 */
@Entity
public class Travel implements Serializable {


    @Id
    @GeneratedValue
    private Long id;
    private int number;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Status status;
    private int costCenterNumber;
    private int employerNumber;

    public Travel(Long id, int number, String description, LocalDate startDate, LocalDate endDate, Status status, int costCenterNumber, int employerNumber) {
        this.id = id;
        this.number = number;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.costCenterNumber = costCenterNumber;
        this.employerNumber = employerNumber;
    }

    public Travel(int number, String description, LocalDate startDate, LocalDate endDate, Status status, int costCenterNumber, int employerNumber) {
        this.number = number;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.costCenterNumber = costCenterNumber;
        this.employerNumber = employerNumber;
    }

    public Travel() {
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

    public int getCostCenterNumber() {
        return costCenterNumber;
    }

    public int getEmployerNumber() {
        return employerNumber;
    }

    @Override
    public String toString() {
        return "TravelTO{" +
                "id=" + id +
                ", number=" + number +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                ", costCenterNumber=" + costCenterNumber +
                ", employerNumber=" + employerNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Travel travel = (Travel) o;

        if (number != travel.number) return false;
        if (costCenterNumber != travel.costCenterNumber) return false;
        if (employerNumber != travel.employerNumber) return false;
        if (!id.equals(travel.id)) return false;
        if (!description.equals(travel.description)) return false;
        if (!startDate.equals(travel.startDate)) return false;
        if (!endDate.equals(travel.endDate)) return false;
        return status == travel.status;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + number;
        result = 31 * result + description.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + costCenterNumber;
        result = 31 * result + employerNumber;
        return result;
    }
}
