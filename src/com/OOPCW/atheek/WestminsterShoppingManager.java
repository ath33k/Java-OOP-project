package com.OOPCW.atheek;

import java.util.ArrayList;
import java.util.List;

public class WestminsterShoppingManager implements ShoppingManager {
    private List<Product> productList = new ArrayList<>();
    public static WestminsterShoppingManager uowShoppingManager = new WestminsterShoppingManager();

    private WestminsterShoppingManager() {}

    public void menu(){
        System.out.println("----- Welcome to Westminster Shopping Centre -----");
        System.out.println("Please select a number below:");
        System.out.println("1. Add a product");
        System.out.println("2. Delete a product");
        System.out.println("3. Display all the products");
        System.out.println("4. Save the file");
        System.out.println("5. To exit");
    }

    public static WestminsterShoppingManager getUowShoppingManager() {
        return uowShoppingManager;
    }

    @Override
    public void addProduct(Product product) {

    }

    @Override
    public void deleteProduct(Product product) {

    }

    @Override
    public void printAllProducts() {

    }

    @Override
    public void saveFile() {

    }

    @Override
    public String toString() {
        return "WestminsterShoppingManager{" +
                "productList=" + productList +
                '}';
    }
}
