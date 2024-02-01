package com.example.receiptprocessor;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private List<Product> products;

    public Receipt() {
        this.products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
