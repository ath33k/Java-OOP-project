package com.OOPCW.atheek;

import java.util.ArrayList;
import java.util.List;

public class WestminsterShoppingManager implements ShoppingManager {
    private List<Product> productList = new ArrayList<>();

    private static final int MAX_PRODUCT = 2;
    int productCount;
//    public static WestminsterShoppingManager uowShoppingManager = new WestminsterShoppingManager();

//    private WestminsterShoppingManager() {}

//    public static WestminsterShoppingManager getUowShoppingManager() {
//        return uowShoppingManager;
//    }

    @Override
    public void addProduct(Product product) {
        if (MAX_PRODUCT > productCount){
//            It avoids adding same product to the list (checks for same product id) by iterating through each objects in the list
            for (Product currProduct: productList){
                if (currProduct.equals(product)){
                    System.out.println("This product Already Have it in the list");
                    return;
                }
            }
            productList.add(product);
            productCount++;
        }else {
            System.out.println("Product list is full");
        }


    }

    @Override
    public void deleteProduct(String id) {
        if (productList.size() > 0) {
            for (Product currProduct : productList) {
                if (currProduct.productID.equals(id)) {
                    String className = currProduct.getClass().getName();
                    String classTypeMsg = className.equals("Clothing") ?
                            "You have removed a clothing product" :
                            "You have removed a electronic product";
                    System.out.println(classTypeMsg);
                    System.out.println(currProduct.toString());
                    productList.remove(currProduct);
                    System.out.println(productList.size() + " products left in the list");
                } else {
                    System.out.println("The product you wanted to delete not in this list");
                }
            }
        }else{
            System.out.println("Product list is empty");
        }
    }

    @Override
    public void printAllProducts() {
        for (Product currProduct : productList){
            String className = currProduct.getClass().getName();
            String classTypeMsg = className.equals("Clothing") ?
                    "It's a clothing product" :
                    "It's a electronic product";
            System.out.println(classTypeMsg);
            System.out.println(currProduct.toString());
        }
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

    public void menu(){
        System.out.println("----- Welcome to Westminster Shopping Centre -----");
        System.out.println("Please select a number below:");
        System.out.println("1. Add a product");
        System.out.println("2. Delete a product");
        System.out.println("3. Display all the products");
        System.out.println("4. Save the file");
        System.out.println("5. To exit");
    }
}
