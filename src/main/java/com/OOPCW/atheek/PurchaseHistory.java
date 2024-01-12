package com.OOPCW.atheek;

import java.util.ArrayList;
import java.util.List;

public class PurchaseHistory {
    private List<Product> purchases = new ArrayList<>();

    private int purchaseCount;

    void addPurchasedProducts(Product product){
        if (!purchases.isEmpty()) {
            for (Product currProduct : purchases) {
                if (currProduct.productID.equals(product.productID)) {
                    currProduct.itemsInCart += product.getItemsInCart();
                    return;
                }
            }
        }
        setPurchases(product);
        purchaseCount++;
    }

    void clearPurchasedProducts(){
        purchases.clear();
        purchaseCount = 0;
    }

    public List<Product> getPurchases() {
        return purchases;
    }

    public void setPurchases(Product product) {
        purchases.add(product);
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(int purchaseCount) {
        this.purchaseCount = purchaseCount;
    }
}
