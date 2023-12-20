package com.OOPCW.atheek;

import java.awt.*;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        WestminsterShoppingManager uowShoppingManager = new WestminsterShoppingManager();

        label:
            while(true){
                uowShoppingManager.menu();
                int mainMenuChoice = validateIntegers(">>> ",10);

                switch (mainMenuChoice){
                    case 1:
//                        System.out.println("Enter '1' to add electronics OR Enter '2' to add Clothing");
                        int nestedChoice = validateIntegers("Enter '1' to add electronics OR Enter '2' to add Clothing \n>>> ",5);
                        sc.nextLine();
                        if (nestedChoice == 1){
                            uowShoppingManager.addProduct(addElectronics());
                        }else if (nestedChoice == 2){
                            uowShoppingManager.addProduct(addClothing());
                        }else {
                            System.out.println("Unknown operation");
                        }
                        break;
                    case 2:
                        uowShoppingManager.deleteProduct(deleteOption());
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break label;
                    default:
                        System.out.println("Invalid Input");
                        break;
                }
//                System.out.println("Press any number to navigate main menu Or enter '999' to exit : ");
                int extChoice = validateIntegers("Press any number to navigate main menu Or enter '999' to exit : ",1000);
                if (extChoice == 999){
                    uowShoppingManager.printAllProducts();
                    break;
                }


            }


    }

    public static Clothing addClothing(){
//        System.out.println("Enter Product ID : ");
        String productID = stringValidation("Enter Product ID : ",10);
//        System.out.println("Enter Product name : ");
        String productName = stringValidation("Enter Product name : ",50);
//        System.out.println("Enter the No of Items : ");
        int noOfItems = validateIntegers("Enter the No of Items : ",50);
//        System.out.println("Enter the price : ");
        sc.nextLine();
        double price = validateDoubles("Enter the price : ",10000000);
        sc.nextLine();
//        System.out.print("Enter the size : ");
        String size = stringValidation("Enter the size : ",20);
        System.out.println("Enter the colours in RED, GREEN, BLUE values from (0 to 255)");
//        System.out.println("RED : ");
        int red = validateIntegers("RED : ",255);
//        System.out.println("GREEN : ");
        int green = validateIntegers("GREEN : ",255);
//        System.out.println("BLUE : ");
        int blue = validateIntegers("BLUE : ",255);
        sc.nextLine();
        return new Clothing(productID,productName,noOfItems,price,size,new Color(red,green,blue));
    }

    public static Electronics addElectronics(){
//        System.out.println("Enter Product ID : ");
        String productID = stringValidation("Enter Product ID : ",10);
//        System.out.println("Enter Product name : ");
        String productName = stringValidation("Enter Product name : ",50);
//        System.out.println("Enter the No of Items : ");
        int noOfItems = validateIntegers("Enter the No of Items : ",50);
//        System.out.println("Enter the price : ");
        double price = validateDoubles("Enter the price : ",10000000);
        sc.nextLine();
//        System.out.print("Enter the Brand : ");
        String brand = stringValidation("Enter the Brand : ",20);
//        System.out.println("Enter the warranty period in months");
        int warranty = validateIntegers("Enter the warranty period in months : ",200);
        sc.nextLine();
        return new Electronics(productID,productName,noOfItems,price,brand,warranty);
    }

    public static String deleteOption(){
        String productID = stringValidation("Enter the desired product ID that you want to delete : ",10);
        return productID;
    }


    /** Input Validators including (int, double, String)*/
    public static int validateIntegers(String message, int range){
//        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            try {
                int res = sc.nextInt();
                if ( res <= range ){
                    return res;
                }else {
                    System.out.println("Out of range");
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Please enter an integer value");

            }
        }
    }

    public static double validateDoubles(String message, int range){
//        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            try {
                double res = sc.nextDouble();
                if ( res <= range ){
                    return res;
                }else {
                    System.out.println("Out of range");
                }
            } catch (Exception e) {
                System.out.println("Please enter number values");
                sc.nextLine();
            }
        }
    }

    private static String stringValidation(String message, int range){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.print(message);
            String res = sc.nextLine();

            if (Pattern.matches("[A-Za-z0-9]+",res)){
                if ( res.length() <= range ){
                    return res;
                }
            }else if (Pattern.matches("(\\s*[A-Za-z0-9]*\\s*)+",res)){
                System.out.println("Please DO NOT enter white spaces");
                continue;
            }

            System.out.println("Please enter valid length between from 5 to 10");

        }
    }



}