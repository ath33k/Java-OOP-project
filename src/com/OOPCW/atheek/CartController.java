package com.OOPCW.atheek;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CartController {
    User testUser;
    ShoppingCart cartClass = ShoppingCart.getShoppingCart();
    CartView cartView;
    JTable table;
    ShoppingCentreController spCtrl;

    CartTableEditor cartTableEditor = new CartTableEditor(cartClass.getCart());
//    BtnEventHandler handler = new BtnEventHandler();

//    CartCellEditor newcartcell = new CartCellEditor();

    WestminsterShoppingManager uowModel = WestminsterShoppingManager.getUowShoppingManager();
    public CartController(ShoppingCentreController spCtrl, User testUser){
        cartView = new CartView(this);
        this.table = cartView.table;
        this.spCtrl = spCtrl;
        this.testUser = testUser;
    }

    void cartInit(){
//        cartView.addLayout();
//        cartView.addTable();
//        updateTable();
//        editCell();
        cartView.setTitle("Westminster Shopping Cart");
        cartView.setSize(800,800);
        cartView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cartView.setVisible(true);
//        addListeners();

    }

//    void addListeners(){
//        purchaseBtnEvent();
//    }
//
//    void purchaseBtnEvent(){
//        cartView.purchaseBtn.addActionListener(e -> {
//
//        });
//    }

    void purchaseConfirmed(){
        for (Product product: cartClass.getCart()){
            testUser.getHistory().addPurchasedProducts(product);
        }
        cartClass.clearCart();
        cartLabelUpdate();
    }

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
    public double updateTotalValue(){
        double tot = 0;
        for (int i = 0; i < table.getModel().getRowCount(); i++){
            tot += Double.parseDouble(table.getModel().getValueAt(i,2).toString());
        }
        return tot;
    }

    public double tenPercentDiscount(double total){
        if (testUser.getHistory().getPurchaseCount() < 1){
            return total * 0.1;
        }
        return 0;
    }

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
            // Sets edited value so it will get recieved in the unedited state
            if (aValue instanceof Number){
                if (columnIndex == 1){
                    cartOfProducts.get(rowIndex).setItemsInCart((int)aValue);
                }
                fireTableCellUpdated(rowIndex,2);
                cartLabelUpdate();
                //sending appropriate object and the value that is changed by input
                //So that shopping label under the table get updated
                spCtrl.updateLabels(cartOfProducts.get(rowIndex));
                cartView.purchaseBtn.setEnabled(cartClass.cartCount > 0);
            }
        }


    }





//    public class CartCellEditor extends DefaultCellEditor{
//
//        JSpinner inputSpinner;
//
////        private JTable table;
////        private int row;
////
////        private Product item;
//
//        public CartCellEditor() {
//            super(new JCheckBox());
//            inputSpinner = new JSpinner();
//            SpinnerNumberModel numberModel = (SpinnerNumberModel) inputSpinner.getModel();
//            numberModel.setMinimum(1);
//
//            model = (DefaultTableModel) cartView.table.getModel();
//            table = cartView.table;
//
//            cartView.table.getSelectionModel().addListSelectionListener(e -> {
//
//
//                if (!e.getValueIsAdjusting()){
//                    int row = table.getSelectedRow();
//                    double price = Double.parseDouble(model.getValueAt(row,2).toString());
//                    String CellValue = model.getValueAt(row,0).toString();
//                    int c = 0;
//                    String prId;
//                    for (int i = 0; i <= 10;i++){
//                        if(CellValue.charAt(c) == ' '){
//                            prId = CellValue.substring(0,c);
//                            for (Product product: cartClass.cart){
//                                if (product.productID.equals(prId)){
//                                    int maxValue = product.getNoOfItems();
//                                    numberModel.setMaximum(maxValue);
//                                }
//                            }
//                            break;
//                        }
//                        c++;
//                    }
//
//                    inputSpinner.addChangeListener((ChangeEvent event) -> {
////                        int spinnerQuantity = Integer.parseInt(inputSpinner.getValue().toString());
//                        int qty = Integer.parseInt(model.getValueAt(row,1).toString());
//                        table.setValueAt(qty *  price,row,2);
////                        double sum = 0;
////                        for (int i = 0; i < model.getRowCount(); i++){
////                            sum += Double.parseDouble(model.getValueAt(i,2).toString());
////                        }
////                        cartView.totalNum.setText(Double.toString(sum));
//                        calculateTotal();
//                    });
//
//                }
//
//
//
//
//            });
//
//
//            JSpinner.NumberEditor editor = (JSpinner.NumberEditor)inputSpinner.getEditor();
//            DefaultFormatter formatter = (DefaultFormatter)editor.getTextField().getFormatter();
//            formatter.setCommitsOnValidEdit(true);
//        }
//
//        /**
//         * This method works When cell begin editing
//         * Override this method to get JSpinner instead of default JCheckbox component*/
//        @Override
//        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//             super.getTableCellEditorComponent(table, value, isSelected, row, column);
////             this.table = table;
////             this.row = row;
////             this.item = (Product) table.getValueAt(row,0);
//             int quantity = Integer.parseInt(value.toString());
//             inputSpinner.setValue(quantity);
//             return inputSpinner;
//        }
//
//        /**This method work when the cell has stopped editing
//         * So by returning the input spinner value will modify the value of the cell*/
//        @Override
//        public Object getCellEditorValue() {
//            return inputSpinner.getValue();
//        }
//
//    }

}
