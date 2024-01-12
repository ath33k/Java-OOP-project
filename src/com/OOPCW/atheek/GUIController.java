package com.OOPCW.atheek;

public class GUIController {
    ShoppingCentreController shoppingCentreCTRL;
    CartController cartController;

    void start(){
        shoppingCentreCTRL = new ShoppingCentreController();
        cartController = new CartController(shoppingCentreCTRL);
        shoppingCentreCTRL.mainInit(cartController);

    }
}
