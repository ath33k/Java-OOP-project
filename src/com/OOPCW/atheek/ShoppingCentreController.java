package com.OOPCW.atheek;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ShoppingCentreController {
    ShoppingCentreView shoppingView;

    WestminsterShoppingManager uowModel = WestminsterShoppingManager.getUowShoppingManager();

    CartController CartCtrl;
//    DefaultTableModel model;
    JTable table;

    ProductTableEditor productTableEditor = new ProductTableEditor(uowModel.productList);

    TableRowSorter<TableModel> tableSorter = new TableRowSorter<>(productTableEditor);



    public ShoppingCentreController() {
        shoppingView = new ShoppingCentreView(this);
        this.table = shoppingView.table;
    }

    void mainInit(CartController spCartCtrl){
        this.CartCtrl = spCartCtrl;
        shoppingView.setTitle("Westminster Shopping Centre");
        shoppingView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension minimumSize = new Dimension(1024,800);
        shoppingView.setMinimumSize(minimumSize);
        shoppingView.setResizable(!(shoppingView.getSize().getWidth() < minimumSize.getWidth()));
        shoppingView.setResizable(!(shoppingView.getSize().getHeight() < minimumSize.getHeight()));
        shoppingView.setExtendedState(JFrame.MAXIMIZED_BOTH);
        shoppingView.setVisible(true);
        addEventListeners();
        setFilter();
        table.setRowSorter(tableSorter);
    }



    public class ProductTableEditor extends AbstractTableModel{
        List<Product> listOfProducts;

        public ProductTableEditor(List<Product> listOfProducts) {
            this.listOfProducts = listOfProducts;
        }


        @Override
        public int getRowCount() {
            return listOfProducts.size();
        }

        @Override
        public int getColumnCount() {
            return 5;
        }


        @Override
        public String getColumnName(int column) {
            switch (column){
                case 0: return "Product ID";
                case 1: return "Name";
                case 2: return "Category";
                case 3: return "Price";
                case 4: return "Info";
                default: return null;
            }
        }


        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex){
                case 0, 1, 2, 4: return String.class;
                case 3: return Double.class;
                default: return null;
            }
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            try {
                Product product = listOfProducts.get(rowIndex);
                System.out.println(listOfProducts.size());
                switch (columnIndex) {
                    case 0:
                        return product.getProductID();
                    case 1:
                        return product.getProductName();
                    case 2:
                        return product.getClass().getSimpleName();
                    case 3:
                        return product.getPrice();
                    case 4:
                        if (product.getClass().getSimpleName().equals("Electronics")) {
                            return ((Electronics) product).getBrand() + ", " + ((Electronics) product).getWarrantyPeriod() + " Weeks Warranty";
                        } else if (product.getClass().getSimpleName().equals("Clothing")) {
                            return ((Clothing) product).getSize() + ", " + ((Clothing) product).getColour();
                        }
                    default:
                        return null;
                }
            }catch (IndexOutOfBoundsException e){
                System.out.println("curr row index" + rowIndex);
                System.out.println("size" + listOfProducts.size());
            }
            return null;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            fireTableRowsUpdated(rowIndex,rowIndex);
        }
    }



    void addEventListeners(){
        tableSelectionEvent();
        cartFrameEvent();
        addToCartEvent();
    }


    void setFilter(){
        shoppingView.comboBox.addActionListener(new ActionListener() {
//            TableRowSorter<TableModel> model = new TableRowSorter<>(productTableEditor);
            @Override
            public void actionPerformed(ActionEvent e) {

                String value = (String) shoppingView.comboBox.getSelectedItem();

                if (value.equals("Electronics")){
                    tableSorter.setRowFilter(RowFilter.regexFilter(value,2));
                } else if (value.equals("Clothing")) {
                    tableSorter.setRowFilter(RowFilter.regexFilter(value,2));
                }else {
                    tableSorter.setRowFilter(RowFilter.regexFilter(".*",2));
                }
            }
        });
    }


    void addTableColor(){
        DefaultTableCellRenderer colorRenderer = new DefaultTableCellRenderer();
        colorRenderer.setBackground(Color.red);

        for (Product product: uowModel.productList){
            if (product.getNoOfItems() < 3){
                for (int i = 0; i < table.getModel().getRowCount(); i++){
                    String value = table.getModel().getValueAt(i,0).toString();
                    if (value.equals(product.productID)){

                    }
                }
            }
        }

//        table.getColumnModel().getColumn(0).setCellRenderer(colorRenderer);
    }

    void updateLabels(Product product, int value){
        for (Product pr: uowModel.productList){
            if (product.equals(pr)){
                int index = uowModel.productList.indexOf(product);
                labelCodes(table.convertRowIndexToModel(index));
            }
        }
    }

    void tableSelectionEvent(){
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()){
                int row = table.convertRowIndexToModel(table.getSelectedRow());
                labelCodes(row);
            }
        });
    }

    void labelCodes(int row){
        shoppingView.detailsLabel.setText("Selected Product - Details");
        shoppingView.productIdLabel.setText("Product ID : " + productTableEditor.getValueAt(row,0));
        shoppingView.categoryLabel.setText("Category: " + productTableEditor.getValueAt(row,1));
        shoppingView.nameLabel.setText("Name: " + productTableEditor.getValueAt(row,2));
        shoppingView.sizeLabel.setText("Size: " + productTableEditor.getValueAt(row,3));
        shoppingView.colourLabel.setText("Colour: " + productTableEditor.getValueAt(row,4));
        for (Product product: uowModel.productList){
            if (product.productID.equals(productTableEditor.getValueAt(row,0))){
                shoppingView.itemAvailableLabel.setText("Item available: " + product.noOfItems);
            }
        }
//        shoppingView.itemAvailableLabel.setText("Item available: " + uowModel.productList.get(row).noOfItems);
    }

    void cartFrameEvent(){
        shoppingView.cart.addActionListener(e -> {
            CartCtrl.cartInit();
            System.out.println(CartCtrl.cartClass.cartCount);
        });
    }

    void addToCartEvent(){
        shoppingView.addToCartBtn.addActionListener(e -> {
            int row = table.convertRowIndexToModel(table.getSelectedRow());
            String value = productTableEditor.getValueAt(row,0).toString();
            System.out.println(productTableEditor.getValueAt(row,0).getClass().getSimpleName());
            for (Product product: uowModel.productList){
                if (product.getProductID().equals(value)){
                    System.out.println(product.getClass().getSimpleName());
                    CartCtrl.cartClass.add(product);
                }
            }
        });
    }

}
