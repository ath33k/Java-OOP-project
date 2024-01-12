package com.OOPCW.atheek;

import java.awt.*;
import java.util.Objects;

public class Clothing extends Product {
    private String size;
    private String colour;

    public Clothing(String productID, String productName, int noOfItems, double price, String size, String colour) {
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

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public int getItemsInCart() {
        return super.getItemsInCart();
    }

    @Override
    public void setItemsInCart(int itemCount) {
        super.setItemsInCart(itemCount);
    }

    @Override
    public String toString() {
        return  super.toString() +
                "size='" + size + '\'' +
                ", colour=" + colour +
                " }";
    }

    
}
