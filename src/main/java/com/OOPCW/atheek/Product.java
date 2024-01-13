package com.OOPCW.atheek;

import java.io.Serializable;
import java.util.Objects;

public abstract class Product implements Comparable<Product>, Serializable {
    protected String productID;
    protected String productName;
    protected int noOfItems;
    protected double price;
    protected int copyOfItemsCount;
    protected int itemsInCart;
    protected int purchasedQuantity;

    protected Product(String productID, String productName, int noOfItems, double price) {
        this.productID = productID;
        this.productName = productName;
        this.noOfItems = noOfItems;
        this.price = price;
        this.itemsInCart = 0;
        this.copyOfItemsCount = noOfItems;
    }


    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getNoOfItems() {
        return noOfItems;
    }

    public void setNoOfItems(int noOfItems) {
        this.noOfItems = noOfItems;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getItemsInCart() {
        return itemsInCart;
    }

    public void setItemsInCart(int itemCount) {
        this.noOfItems = copyOfItemsCount;
        if (noOfItems >= itemCount && itemCount > 0){
            this.noOfItems -= itemCount;
            this.itemsInCart = itemCount;
            return;
        }
        this.itemsInCart = 0;
    }

    @Override
    public String toString() {
        return "{ productID='" + productID + '\'' +
                ", productName='" + productName + '\'' +
                ", noOfItems=" + noOfItems +
                ", price=" + price + ", ";
    }




    /** Override the equals method
     * this will check the parameter object whether it is instance of the product class
     * then it will compare the product id of the both class*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(productID, product.productID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID);
    }

    @Override
    public int compareTo(Product other) {
        return productID.compareTo(other.productID);
    }

}
