package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String manufacturer;

    @NotNull
    private String model;

    @NotNull
    private int year;

    @NotNull
    private double msrp;

    @ManyToOne
    private Category category;

    public Car() {
    }

    public Car(@NotNull String manufacturer, @NotNull String model, @NotNull int year, @NotNull double msrp) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.msrp = msrp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getMsrp() {
        return msrp;
    }

    public void setMsrp(double msrp) {
        this.msrp = msrp;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
