package com.example.receiptprocessor;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    private String name;
    private boolean domestic;
    private double price;
    private double weight;
    private String description;

    @JsonCreator
    public Product(
            @JsonProperty("name") String name,
            @JsonProperty("domestic") boolean domestic,
            @JsonProperty("price") double price,
            @JsonProperty("weight") int weight,
            @JsonProperty("description") String description) {
        this.name = name;
        this.domestic = domestic;
        this.price = price;
        this.weight = weight;
        this.description = description;
    }

//    public Product(String name, boolean domestic, double price, double weight, String description) {
//        this.name = name;
//        this.domestic = domestic;
//        this.price = price;
//        this.weight = weight;
//        this.description = description;
//    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDomestic() {
        return domestic;
    }

    public void setDomestic(boolean domestic) {
        this.domestic = domestic;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
