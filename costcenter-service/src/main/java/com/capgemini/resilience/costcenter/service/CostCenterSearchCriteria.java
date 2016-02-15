package com.capgemini.resilience.costcenter.service;

import java.io.Serializable;

import org.springframework.util.StringUtils;

/**
 * Created by kso on 15.02.16.
 */
public class CostCenterSearchCriteria implements Serializable {
    Integer number;
    String name;

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

    @Override
    public String toString() {
        return "CostCenterSearchCriteria{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }

    public boolean hasName() {
        return StringUtils.hasText(name);
    }

    public boolean hasNumber() {
        return number != null;
    }
}
