package com.OOPCW.atheek;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CartView extends JFrame {
    CartController spCartCtrl;
    JPanel mainPanel;
    JPanel topPanel;
    JPanel bottomPanel;
    JPanel bottomInsidePanel;
    JTable table;

    JScrollPane scrollPane;
    JLabel totalText;

    JLabel discount10Text;

    JLabel discount20Text;

    JLabel finalTotText;

    JLabel totalNum;

    JLabel discount10;

    JLabel discount20;

    JLabel finalTotNum;

//    CartCellEditor cellEditor = new CartCellEditor();

    public CartView(CartController spCartCtrl) {
        this.spCartCtrl = spCartCtrl;
        addLayout();
        addTable();
        bottomLabels();
    }

    void addLayout(){
        mainPanel = new JPanel(new GridLayout(2,1));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50,50,20,50));

        topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomInsidePanel = new JPanel(new GridLayout(4,2,50,50));

        bottomPanel.add(bottomInsidePanel);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(100,50,50,50));
        mainPanel.add(topPanel);
        mainPanel.add(bottomPanel);


        add(mainPanel);


    }

    void addTable(){
        table = new JTable(spCartCtrl.cartTableEditor);
        scrollPane = new JScrollPane(table);

        scrollPane.setPreferredSize(new Dimension(800,400));
        table.setFont(new Font("Arial",Font.PLAIN,16));
        table.setGridColor(Color.black);
        table.getTableHeader().setFont(new Font("Arial",Font.BOLD,18));
        table.setRowHeight(30);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);


        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);


        topPanel.add(scrollPane);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

//--------------------------------------------------------------
//    public class CartCellEditor extends DefaultCellEditor{
//
//        JSpinner inputSpinner;
//        private JTable aTable;
//        private int row;
//        private Object item;
//
//        public CartCellEditor() {
//            super(new JCheckBox());
//            inputSpinner = new JSpinner();
//            SpinnerNumberModel numberModel = (SpinnerNumberModel) inputSpinner.getModel();
//            numberModel.setMinimum(1);
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
//             this.aTable = table;
//             this.row = row;
//             this.item = table.getValueAt(row,0);
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



    void bottomLabels(){
        totalText = new JLabel("Total");
        totalNum = new JLabel("0");

        discount10Text = new JLabel("First Purchase Discount (10%)");
        discount10 = new JLabel("0");

        discount20Text = new JLabel("Three Items in same Category Discount (20%)");
        discount20 = new JLabel("0");

        finalTotText = new JLabel("Final Total");
        finalTotNum = new JLabel();

        bottomInsidePanel.setBorder(BorderFactory.createEmptyBorder(10,100,10,50));

        bottomInsidePanel.add(totalText);
        bottomInsidePanel.add(totalNum);
        bottomInsidePanel.add(discount10Text);
        bottomInsidePanel.add(discount10);
        bottomInsidePanel.add(discount20Text);
        bottomInsidePanel.add(discount20);
        bottomInsidePanel.add(finalTotText);
        bottomInsidePanel.add(finalTotNum);
    }


}
