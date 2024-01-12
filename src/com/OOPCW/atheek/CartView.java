package com.OOPCW.atheek;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CartView extends JFrame {
    CartController spCartCtrl;
    JPanel mainPanel;
    JPanel topPanel;

    JPanel bottomInsideContainer;
    JPanel mainContainer;
    JPanel bottomPanel;
    JPanel bottomInsidePanel;

    JPanel bottombuttonPanel;
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

    JButton purchaseBtn;
    BtnEventHandler handler;

//    CartCellEditor cellEditor = new CartCellEditor();

    public CartView(CartController spCartCtrl) {
        this.spCartCtrl = spCartCtrl;
        addLayout();
        addTable();
        bottomLabels();
        handler = new BtnEventHandler();
        purchaseBtn.addActionListener(handler);
    }

    void addLayout(){
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        mainContainer = new JPanel(new GridLayout(2,1));
        mainContainer.setBorder(BorderFactory.createEmptyBorder(5,50,5,50));
        mainContainer.setBackground(Color.YELLOW);
        topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(20,50,5,50));
        topPanel.setBackground(Color.red);
        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.blue);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5,100,5,50));
        bottomInsideContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomInsideContainer.setBackground(Color.GREEN);
        bottomInsidePanel = new JPanel(new GridLayout(4,2,50,20));
        bottomInsidePanel.setBackground(Color.magenta);
        bottombuttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));




        bottomPanel.add(bottomInsideContainer,BorderLayout.NORTH);
        bottomPanel.add(bottombuttonPanel, BorderLayout.SOUTH);
        bottomInsideContainer.add(bottomInsidePanel);
        mainContainer.add(topPanel);
        mainContainer.add(bottomPanel);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(100,50,50,50));
        mainPanel.add(mainContainer, BorderLayout.CENTER);


        add(mainPanel);


    }

    void addTable(){
        table = new JTable(spCartCtrl.cartTableEditor);
        scrollPane = new JScrollPane(table);

        scrollPane.setPreferredSize(new Dimension(800,300));
        table.setFont(new Font("Arial",Font.PLAIN,16));
        table.setGridColor(Color.black);
        table.getTableHeader().setFont(new Font("Arial",Font.BOLD,18));
        table.setRowHeight(30);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);


        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);


        topPanel.add(scrollPane,BorderLayout.CENTER);
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
        purchaseBtn = new JButton("Purchase");
        purchaseBtn.setEnabled(spCartCtrl.cartClass.cartCount > 0);
        bottombuttonPanel.add(purchaseBtn);
    }

    public class BtnEventHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String message = "Are you sure you want to purchase?";
            int result = JOptionPane.showConfirmDialog(null, message,"Purchase Confirmation",JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION){
                spCartCtrl.purchaseConfirmed();
            } else {
                System.out.println("No");
            }
        }
    }

}
