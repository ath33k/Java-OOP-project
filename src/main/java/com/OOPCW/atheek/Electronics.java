package com.OOPCW.atheek;


public class Electronics extends Product{
    private String brand;
    private int warrantyPeriod;

    public Electronics(String productID, String productName, int noOfItems, double price, String brand, int warrantyPeriod) {
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

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
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
        return super.toString() +
                "brand='" + brand + '\'' +
                ", warrantyPeriod=" + warrantyPeriod +
                " }";
    }


}
