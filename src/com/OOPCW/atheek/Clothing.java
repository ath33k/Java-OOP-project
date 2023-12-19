package com.OOPCW.atheek;

import java.awt.*;

public class Clothing extends Product {
    private String size;
    private Color colour;

    public Clothing(String productID, String productName, int noOfItems, double price, String size, Color colour) {
        super(productID, productName, noOfItems, price);
        this.size = size;
        this.colour = colour;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "com.OOPCW.atheek.Clothing{" +
                "size='" + size + '\'' +
                ", colour=" + colour +
                ", productID='" + productID + '\'' +
                ", productName='" + productName + '\'' +
                ", noOfItems=" + noOfItems +
                ", price=" + price +
                '}';
    }
}
