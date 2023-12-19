package com.OOPCW.atheek;

import java.util.Date;

public class Electronics extends Product{
    private String brand;
    private Date warrantyPeriod;

    public Electronics(String productID, String productName, int noOfItems, double price, String brand, Date warrantyPeriod) {
        super(productID, productName, noOfItems, price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Date getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(Date warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public String toString() {
        return "com.OOPCW.atheek.Electronics{" +
                "brand='" + brand + '\'' +
                ", warrantyPeriod=" + warrantyPeriod +
                ", productID='" + productID + '\'' +
                ", productName='" + productName + '\'' +
                ", noOfItems=" + noOfItems +
                ", price=" + price +
                '}';
    }
}
