package com.example.receiptprocessor;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ReceiptProcessor {
    Receipt receipt;
    private static DecimalFormat decimalFormat = new DecimalFormat("#");
    private static final int MAX_DESCRIPTION_LENGTH=10;

    public ReceiptProcessor(Receipt receipt) {
        this.receipt = receipt;
    }

    public void groupAndSortProducts() {
        List<Product> domestic = new ArrayList<>();
        List<Product> imported = new ArrayList<>();
        for (Product product : receipt.getProducts()) {
            if (!product.isDomestic()) {
                imported.add(product);
            } else {
                domestic.add(product);
            }
        }
        domestic.sort(Comparator.comparing(Product::getName));
        imported.sort(Comparator.comparing(Product::getName));

        printProducts("Domestic", domestic);
        printProducts("Imported", imported);
    }

    private void printProducts(String origin, List<Product> products) {
        System.out.println("." + origin);
        for (Product product : products) {
            System.out.println("...\t" + product.getName());
            System.out.println("\tPrice: $" + product.getPrice());
            String truncatedDescription=product.getDescription().length() > MAX_DESCRIPTION_LENGTH ? product.getDescription().substring(0,MAX_DESCRIPTION_LENGTH)+"..." : product.getDescription();
            System.out.println("\t"+truncatedDescription);
            System.out.println("\tWeight: " + (product.getWeight()==0 ? "N/A": decimalFormat.format(product.getWeight())+"g"));

        }
    }

    public void calculateTotals(){
        double domesticTotalCost=0;
        double importedTotalCost=0;
        int domesticCount=0;
        int importedCount=0;

        for(Product product: receipt.getProducts()){
            if(product.isDomestic()){
                domesticCount++;
                domesticTotalCost+=product.getPrice();
            }else{
                importedCount++;
                importedTotalCost+=product.getPrice();
            }
        }

        System.out.println("Domestic cost: $"+domesticTotalCost);
        System.out.println("Imported cost: $"+importedTotalCost);
        System.out.println("Domestic count: "+domesticCount);
        System.out.println("Imported count: "+importedCount);
    }
}
