package com.OOPCW.atheek;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CartController {
    User testUser;
    ShoppingCart cartClass = ShoppingCart.getShoppingCart();
    CartView cartView;
    JTable table;
    ShoppingCentreController spCtrl;
    CartTableEditor cartTableEditor = new CartTableEditor(cartClass.getCart());

    WestminsterShoppingManager uowModel = WestminsterShoppingManager.getUowShoppingManager();

    /**Constructor of this class
     * which shares composition relationship with CartView*/
    public CartController(ShoppingCentreController spCtrl, User testUser){
        cartView = new CartView(this);
        this.table = cartView.table;
        this.spCtrl = spCtrl;
        this.testUser = testUser;
    }

    /** Make the frame visible here*/
    void cartInit(){
        cartView.setTitle("Westminster Shopping Cart");
        cartView.setSize(1024,800);
        cartView.setResizable(false);
        cartView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cartView.setVisible(true);

    }


    /**This code will get called by purchase btn in the cartView
     * when button clicked.
     * this will add products to the purchases cart and reduces the value of productList,
     * Clear the shopping cart, to make it visible as well as fire the table changed method to refresh the table
     * */
    void purchaseConfirmed(){
        for (Product product: cartClass.getCart()){
            product.purchasedQuantity = product.getItemsInCart();
            product.copyOfItemsCount -= product.getItemsInCart();
            testUser.getHistory().addPurchasedProducts(product);
        }
        for (Product product : cartClass.getCart()) {
            cartTableEditor.setValueAt("None",cartClass.getCart().indexOf(product),1 );
        }
        cartClass.clearCart();
        cartTableEditor.fireTableDataChanged();
        cartLabelUpdate();
    }


    /**All in one method to update the labels in shopping cart*/
    public void cartLabelUpdate(){
        double total = updateTotalValue();
        double discountValue10 = tenPercentDiscount(total);
        double discountValue20 = twentyPercentDiscount(total);
        double finalTotal = (total - discountValue10) - discountValue20;
        cartView.totalNum.setText(Double.toString(total));
        cartView.discount10.setText(Double.toString(discountValue10));
        cartView.discount20.setText(Double.toString(discountValue20));
        cartView.finalTotNum.setText(Double.toString(finalTotal));
    }

    /** Updating total value method*/
    public double updateTotalValue(){
        double tot = 0;
        for (int i = 0; i < table.getModel().getRowCount(); i++){
            tot += Double.parseDouble(table.getModel().getValueAt(i,2).toString());
        }
        return tot;
    }

    /** 10% discount calculating method*/
    public double tenPercentDiscount(double total){
        if (testUser.getHistory().getPurchaseCount() < 1){
            return total * 0.1;
        }
        return 0;
    }

    /** 20% discount calculation method*/
    public double twentyPercentDiscount(double total){
        int electronicCount = 0;
        int clothingCount = 0;
        for (Product product : cartClass.cart){
            if (product.getClass().getSimpleName().equals("Electronics")){
                electronicCount++;
            }else {
                clothingCount++;
            }
        }
        return electronicCount >= 3 || clothingCount >= 3 ? total * 0.1 : 0;
    }

    /** Inner class abstractModel this will operate and make changes in the Jtable  */
    public class CartTableEditor extends AbstractTableModel{
        List<Product> cartOfProducts;

        public CartTableEditor(List<Product> cartOfProducts) {
            this.cartOfProducts = cartOfProducts;
        }

        @Override
        public int getRowCount() {
            // Sets the row count of the table basically the products in the cart
            return cartOfProducts.size();
        }

        @Override
        public int getColumnCount() {
            // sets the column count of the table
            return 3;
        }

        @Override
        public String getColumnName(int column) {
            // Set name to the column
            switch (column){
                case 0: return "Product";
                case 1: return "Quantity";
                case 2: return "Price";
                default: return null;
            }
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            // Define which type should store in the column
            switch (columnIndex){
                case 0: return String.class;
                case 1: return Integer.class;
                case 2: return Double.class;
                default: return Object.class;

            }
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            // only the second cell of the table can be edited
            return columnIndex == 1;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            // simply get the value show it in the table
            Product product = cartOfProducts.get(rowIndex);
            switch (columnIndex){
                case 0:
                    if (product.getClass().getSimpleName().equals("Electronics")){
                      return product.getProductID() + " " +
                              product.getProductName() + " " +
                              ((Electronics)product).getBrand() + " " +
                              ((Electronics)product).getWarrantyPeriod();

                    }else if (product.getClass().getSimpleName().equals("Clothing")){
                        return product.getProductID() + " " +
                                product.getProductName() + " " +
                                ((Clothing)product).getSize() + " " +
                                ((Clothing)product).getColour();
                    }
                case 1: return product.getItemsInCart();
                case 2: return product.getPrice() * product.itemsInCart;
                default: return null;
            }
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            // Sets edited value so it will get received in the unedited state
            if (aValue instanceof Number){
                if (columnIndex == 1){
                    cartOfProducts.get(rowIndex).setItemsInCart((int)aValue);
                }
                fireTableCellUpdated(rowIndex,2);

            }else{
                fireTableCellUpdated(rowIndex,1);
                cartOfProducts.get(rowIndex).setItemsInCart(0);
            }
            cartLabelUpdate();
            //sending appropriate object and the value that is changed by input
            //So that shopping label under the table get updated
            spCtrl.updateLabels(cartOfProducts.get(rowIndex));
            cartView.purchaseBtn.setEnabled(cartClass.cartCount > 0);
        }
    }

}
