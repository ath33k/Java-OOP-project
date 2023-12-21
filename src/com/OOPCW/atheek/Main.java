package com.OOPCW.atheek;

import java.awt.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Main {
    static Scanner sc = new Scanner(System.in);
    static WestminsterShoppingManager uowShoppingManager = WestminsterShoppingManager.getUowShoppingManager(); //Singleton pattern usage

    public static void main(String[] args)  {

        try {
            uowShoppingManager.loadFile(); //Load products from a file that previously saved.
        } catch (Exception ignored) {}     // Exception will get ignored whether it is (IOException or ClassNotFoundException)

        menuLabel:
            while(true){
                uowShoppingManager.menu();
                int mainMenuChoice = validateIntegers(">>> ",0,10);

                switch (mainMenuChoice){
                    case 1:
                        int nestedChoice = validateIntegers("Enter '1' to add electronics OR Enter '2' to add Clothing \n>>> ",1,2);
                        sc.nextLine();
                        if (nestedChoice == 1){
                            uowShoppingManager.addProduct(addElectronics());
                        }else if (nestedChoice == 2){
                            uowShoppingManager.addProduct(addClothing());
                        }
                        break;
                    case 2:
                        uowShoppingManager.deleteProduct(deleteOption());
                        break;
                    case 3:
                        uowShoppingManager.printAllProducts();
                        break;
                    case 4:
                        try {
                            uowShoppingManager.saveFile();
                        } catch (IOException e) {
                            System.out.println("Couldn't save the file");
                        }
                        break;
                    case 5:
                        break menuLabel;
                    default:
                        System.out.println("Invalid Input");
                        break;
                }
                int extChoice = validateIntegers("Press any number to navigate main menu Or enter '999' to exit : ",1,1000);
                if (extChoice == 999){
                    uowShoppingManager.printAllProducts();
                    break;
                }
            }
    }


    public static Clothing addClothing(){
        String productID = stringValidation("Enter Product ID : ",5,10);
        String productName = stringValidation("Enter Product name : ",1,50);
        int noOfItems = validateIntegers("Enter the No of Items : ",1,50);
        sc.nextLine();
        double price = validateDoubles("Enter the price : ",1,10000000);
        sc.nextLine();
        String size = stringValidation("Enter the size : ",1,20);
        System.out.println("Enter the colours in RED, GREEN, BLUE values from (0 to 255)");
        int red = validateIntegers("RED : ",0,255);
        int green = validateIntegers("GREEN : ",0,255);
        int blue = validateIntegers("BLUE : ",0,255);
        sc.nextLine();
        return new Clothing(productID,productName,noOfItems,price,size,new Color(red,green,blue));
    }


    public static Electronics addElectronics(){
        String productID = stringValidation("Enter Product ID : ",5,10);
        String productName = stringValidation("Enter Product name : ",1,50);
        int noOfItems = validateIntegers("Enter the No of Items : ",1,50);
        double price = validateDoubles("Enter the price : ",1,10000000);
        sc.nextLine();
        String brand = stringValidation("Enter the Brand : ",1,20);
        int warranty = validateIntegers("Enter the warranty period in months : ",0,200);
        sc.nextLine();
        return new Electronics(productID,productName,noOfItems,price,brand,warranty);
    }


    public static String deleteOption(){
        return stringValidation("Enter the desired product ID that you want to delete : ",5,10);

    }


    /** Input Validators including (int, double, String)*/
    public static int validateIntegers(String message, int startRange ,int endRange){
        while (true) {
            System.out.print(message);
            try {
                int res = sc.nextInt();
                if ( res >= startRange && res <= endRange ){
                    return res;
                }else {
                    System.out.println("Please enter number range from " + startRange + " to " + endRange);
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Please enter an integer value");
            }
        }
    }


    public static double validateDoubles(String message, int startRange,int endRange){
        while (true) {
            System.out.print(message);
            try {
                double res = sc.nextDouble();
                if ( res >= startRange && res <= endRange ){
                    return res;
                }else {
                    System.out.println("Please enter number range from " + startRange + " to " + endRange);
                }
            } catch (Exception e) {
                System.out.println("Please enter number values");
                sc.nextLine();
            }
        }
    }


    private static String stringValidation(String message, int startRange,int endRange){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.print(message);
            String res = sc.nextLine().trim();

            if (Pattern.matches("[A-Za-z0-9\\s]+",res)){
                if ( res.length() >= startRange && res.length() <= endRange ){
                    return res;
                }
            }else{
                System.out.println("Please Enter valid characters");
                continue;
            }

            System.out.println("Please enter characters length from " + startRange + " to "+ endRange);
        }
    }

}