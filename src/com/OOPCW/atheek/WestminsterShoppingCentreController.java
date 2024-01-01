package com.OOPCW.atheek;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;

public class WestminsterShoppingCentreController {
    JFrame mainFrame = new JFrame();

    WestminsterShoppingCentre sp;

    DefaultTableModel model;
    JTable table;

    public WestminsterShoppingCentreController() {
        sp = new WestminsterShoppingCentre(mainFrame);

    }

    void mainInit(){
        sp.addLayouts();
        sp.addComponentHead();
        sp.addComponentBody();
        sp.addComponentFooter();
        this.mainFrame.setTitle("Westminster Shopping Centre");
        this.mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.mainFrame.setSize(800,800);
        this.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.mainFrame.setResizable(true);
        this.mainFrame.setVisible(true);
        addEventListeners();
    }

    void addEventListeners(){
        tableSelectionEvent();
    }

    void tableSelectionEvent(){
        sp.table.getSelectionModel().addListSelectionListener(e -> {
            model = (DefaultTableModel) sp.table.getModel();
            table = sp.table;

            if (!e.getValueIsAdjusting()){
                int row = table.getSelectedRow();
                System.out.println("Row selection : " + Arrays.toString(table.getSelectedRows()));
                sp.detailsLabel.setText("Selected Product - Details");
                sp.productIdLabel.setText("Product ID : " + model.getValueAt(row, 0));
                sp.categoryLabel.setText("Category: " + model.getValueAt(row, 1));
                sp.nameLabel.setText("Name: " + model.getValueAt(row, 2));
                sp.sizeLabel.setText("Size: " + model.getValueAt(row, 3));
                sp.colourLabel.setText("Colour: " + model.getValueAt(row, 4));
                sp.itemAvailableLabel.setText("Item available: ");
            }
        });
    }

}
