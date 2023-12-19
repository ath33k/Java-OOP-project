package com.OOPCW.atheek;

public class Main {

    public static void main(String[] args) {
        //Singleton pattern to use single instance of the westminster ShoppingManager
        ShoppingManager uowShoppingManager = WestminsterShoppingManager.getUowShoppingManager();
        ShoppingManager spManager = WestminsterShoppingManager.getUowShoppingManager();

        System.out.println("ohkay");

    }
}