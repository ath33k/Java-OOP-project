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

    public void clearCart(){
        this.cart.clear();
    }
    public void setCart(List<Product> cart) {
        this.cart = cart;
    }

    public int getCartCount() {
        return cartCount;
    }

    public void setCartCount(int cartCount) {
        this.cartCount = cartCount;
    }

    public List<Product> getCart() {
        return cart;
    }
}
