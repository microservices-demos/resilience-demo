package com.capgemini.resilience.employer.service;

import java.io.Serializable;

import org.springframework.util.StringUtils;

/**
 * Created by kso on 15.02.16.
 */
public class EmployerSearchCriteria implements Serializable {
    Integer number;
    String name;
    String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasName() {
        return StringUtils.hasText(name);
    }

    public boolean hasFirstName() {
        return StringUtils.hasText(firstName);
    }

    public boolean hasNumber() {
        return number != null;
    }
}
