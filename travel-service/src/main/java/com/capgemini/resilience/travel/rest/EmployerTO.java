package com.capgemini.resilience.travel.rest;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by kso on 15.02.16.
 */
public class EmployerTO implements Serializable {

    private Long id;
    private int number;
    private String name;
    private String firstName;

    public EmployerTO(Long id, int number, String name, String firstname) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.firstName = firstname;
    }


    public EmployerTO(int number, String name, String firstname) {
        this.number = number;
        this.name = name;
        this.firstName = firstname;
    }

    public EmployerTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmployerTO employer = (EmployerTO)o;

        if (number != employer.number) {
            return false;
        }
        if (!id.equals(employer.id)) {
            return false;
        }
        if (!name.equals(employer.name)) {
            return false;
        }
        return firstName.equals(employer.firstName);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + number;
        result = 31 * result + name.hashCode();
        result = 31 * result + firstName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Employer{" +
                "id=" + id +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
