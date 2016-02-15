package com.capgemini.resilience.costcenter.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by kso on 15.02.16.
 */
@Entity
public class CostCenter implements Serializable {


    @Id
    @GeneratedValue
    private Long id;
    private int number;
    private String name;

    public CostCenter(Long id, int number, String name) {
        this.id = id;
        this.number = number;
        this.name = name;
    }

    public CostCenter(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public CostCenter() {
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


    @Override
    public String toString() {
        return "CostCenter{" +
                "id=" + id +
                ", number=" + number +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CostCenter that = (CostCenter)o;

        if (number != that.number) {
            return false;
        }
        if (!id.equals(that.id)) {
            return false;
        }
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + number;
        result = 31 * result + name.hashCode();
        return result;
    }
}
