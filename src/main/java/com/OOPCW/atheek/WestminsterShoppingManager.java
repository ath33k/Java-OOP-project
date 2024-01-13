package com.OOPCW.atheek;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WestminsterShoppingManager implements ShoppingManager {
    protected List<Product> productList = new ArrayList<>();
    private static final int MAX_PRODUCT = 50;
    private final String filename = "savedData.txt";
    public int productCount;
    public static WestminsterShoppingManager uowShoppingManager = new WestminsterShoppingManager();

    /**Used singleton pattern because this class should have only one object
     * and to avoid instantiate of This class from other the class.*/
    private WestminsterShoppingManager() {}

    public static WestminsterShoppingManager getUowShoppingManager() {
        return uowShoppingManager;
    }

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

    /**This method delete products based on the Product ID
     * Provided by user input*/
    @Override
    public void deleteProduct(String id) {
        if (!productList.isEmpty()) {
            for (Product currProduct : productList) {
                if (currProduct.productID.equalsIgnoreCase(id)) {
                    String className = currProduct.getClass().getName();
                    System.out.println(currProduct);
                    productList.remove(currProduct);
                    String classTypeMsg = className.equals("Clothing") ?
                            "You have removed a clothing product" :
                            "You have removed a electronic product";
                    System.out.println(classTypeMsg);
                    productCount--;
                    System.out.println(productCount + " products left in the list");
                    return;
                }
            }
            System.out.println("The product you wanted to delete not in this list");
        }else{
            System.out.println("Product list is empty");
        }
    }

    /**Prints all products in the product list*/
    @Override
    public void printAllProducts() {
        if (!productList.isEmpty()) {
            Collections.sort(productList);
            for (Product currProduct : productList) {
                String className = currProduct.getClass().getName();
                String classTypeMsg = className.equals("Clothing") ?
                        "It's a clothing product" :
                        "It's a electronic product";
                System.out.println(classTypeMsg);
                System.out.println(currProduct);
            }
        }else {
            System.out.println("List is empty");
        }
    }


    @Override
    public void saveFile() throws IOException {
        if (!productList.isEmpty()){
            FileOutputStream fileOutStr = new FileOutputStream(filename);
            ObjectOutputStream objOutStr = new ObjectOutputStream(fileOutStr);

            for (Product product : productList){
                objOutStr.writeObject(product);
            }
            objOutStr.flush();
            fileOutStr.close();
            objOutStr.close();

        }else {
            System.out.println("Product list is empty");
        }
        System.out.println("All products have saved to the file");

    }


    /** This method save list of products to the file named
     * "saved.txt"*/
    @Override
    public void loadFile() throws IOException, ClassNotFoundException {
        FileInputStream fileInStr = new FileInputStream(filename);
        ObjectInputStream objInStr = new ObjectInputStream(fileInStr);

        for (;;){
            try {
                Product product = (Product) objInStr.readObject();
                productList.add(product);
            }catch (EOFException e){
                break;
            }
        }
        productCount = productList.size();

        fileInStr.close();
        objInStr.close();

        System.out.println("Data loaded successfully from the file");
    }

    @Override
    public String toString() {
        return "WestminsterShoppingManager{" +
                "productList=" + productList +
                '}';
    }

    /**Console menu*/
    public void menu(){
        System.out.println("----- Welcome to Westminster Shopping Centre -----");
        System.out.println("Please select a number below:");
        System.out.println("1. Add a product");
        System.out.println("2. Delete a product");
        System.out.println("3. Display all the products");
        System.out.println("4. Save the file");
        System.out.println("5. Start GUI Application");
        System.out.println("6. To exit");
    }
}
