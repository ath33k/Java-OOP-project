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
                int mainMenuChoice = validateIntegers(">>> ");

                switch (mainMenuChoice){
                    case 1:
//                        System.out.println("Enter '1' to add electronics OR Enter '2' to add Clothing");
                        int nestedChoice = validateIntegers("Enter '1' to add electronics OR Enter '2' to add Clothing \n>>> ");
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
                int extChoice = validateIntegers("Press any number to navigate main menu Or enter '999' to exit : ");
                if (extChoice == 999){
                    uowShoppingManager.printAllProducts();
                    break;
                }


            }


    }

    public static Clothing addClothing(){
//        System.out.println("Enter Product ID : ");
        String productID = stringValidation("Enter Product ID : ");
//        System.out.println("Enter Product name : ");
        String productName = stringValidation("Enter Product name : ");
//        System.out.println("Enter the No of Items : ");
        int noOfItems = validateIntegers("Enter the No of Items : ");
//        System.out.println("Enter the price : ");
        sc.nextLine();
        double price = validateDoubles("Enter the price : ");
        sc.nextLine();
        System.out.println("Enter the size : ");
        String size = sc.nextLine();
        System.out.println("Enter the colours in RED, GREEN, BLUE values from (0 to 255)");
//        System.out.println("RED : ");
        int red = validateIntegers("RED : ");
//        System.out.println("GREEN : ");
        int green = validateIntegers("GREEN : ");
//        System.out.println("BLUE : ");
        int blue = validateIntegers("BLUE : ");
        sc.nextLine();
        return new Clothing(productID,productName,noOfItems,price,size,new Color(red,green,blue));
    }

    public static Electronics addElectronics(){
//        System.out.println("Enter Product ID : ");
        String productID = stringValidation("Enter Product ID : ");
//        System.out.println("Enter Product name : ");
        String productName = stringValidation("Enter Product name : ");
//        System.out.println("Enter the No of Items : ");
        int noOfItems = validateIntegers("Enter the No of Items : ");
//        System.out.println("Enter the price : ");
        double price = validateDoubles("Enter the price : ");
        sc.nextLine();
        System.out.println("Enter the Brand : ");
        String brand = sc.nextLine();
//        System.out.println("Enter the warranty period in months");
        int warranty = validateIntegers("Enter the warranty period in months");
        sc.nextLine();
        return new Electronics(productID,productName,noOfItems,price,brand,warranty);
    }

    public void deleteOption(){

    }


    /** Input Validators including (int, double, String)*/
    public static int validateIntegers(String message){
//        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            try {
                return sc.nextInt();
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Please enter an integer value");

            }
        }
    }

    public static double validateDoubles(String message){
//        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            try {
                return sc.nextDouble();
            } catch (Exception e) {
                System.out.println("Please enter number values");
                sc.nextLine();
            }
        }
    }

    private static String stringValidation(String message){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.print(message);
            String res = sc.nextLine();

            if (Pattern.matches("[A-Za-z0-9]{5,10}",res)){
                return res;
            }else if (Pattern.matches("(\\s*[A-Za-z0-9]*\\s*)+",res)){
                System.out.println("Please DO NOT enter white spaces");
                continue;
            }

            System.out.println("Please enter valid length between from 5 to 10");

        }
    }



}