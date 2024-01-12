package com.OOPCW.atheek;

public class GUIController {
    ShoppingCentreController shoppingCentreCTRL;
    CartController cartController;

    User testUser = new User("Atheek","Naheem");

    void start(){
        shoppingCentreCTRL = new ShoppingCentreController();
        cartController = new CartController(shoppingCentreCTRL,testUser);
        shoppingCentreCTRL.mainInit(cartController);

    }
}
