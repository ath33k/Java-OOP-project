package com.OOPCW.atheek;

import java.util.Objects;

public class Product{
    protected String productID;
    protected String productName;
    protected int noOfItems;
    protected double price;

    protected Product(String productID, String productName, int noOfItems, double price) {
        this.productID = productID;
        this.productName = productName;
        this.noOfItems = noOfItems;
        this.price = price;
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

    /**This method didn't work because it first compares the class type
     * this only works if we add two object of same class in the product list*/
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Product product = (Product) o;
//        return Objects.equals(productID, product.productID);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(productID);
//    }
}
