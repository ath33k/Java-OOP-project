package com.OOPCW.atheek;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    List<Product> cart = new ArrayList<>();

    int cartCount;

    //    Singleton pattern
    public static ShoppingCart shoppingCart = new ShoppingCart();

    private ShoppingCart() {}

    public static ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    void add(Product product){
        if (!cart.isEmpty()) {
            for (Product currProduct : cart) {
                if(currProduct.equals(product)){
                    return;
                }
            }
        }
        cart.add(product);
        cartCount++;
    }
    void remove(){}
    void calculateTotal(){}

    public List<Product> getCart() {
        return cart;
    }
}
