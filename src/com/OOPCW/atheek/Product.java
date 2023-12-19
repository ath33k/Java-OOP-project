package com.OOPCW.atheek;

public class Product {
    protected String productID;
    protected String productName;
    protected int noOfItems;
    protected double price;

    public Product(String productID, String productName, int noOfItems, double price) {
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

//    @Override
//    public String toString() {
//        return "com.OOPCW.atheek.Product{" +
//                "productID='" + productID + '\'' +
//                ", productName='" + productName + '\'' +
//                ", noOfItems=" + noOfItems +
//                ", price=" + price +
//                '}';
//    }
}
