package com.OOPCW.atheek;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class ShoppingCentreView extends JFrame{

    ShoppingCentreController spCtrl;
//    JFrame cartFrame;

    public ShoppingCentreView(ShoppingCentreController spCTRL) {
        this.spCtrl = spCTRL;

        addLayouts();
        addComponentHead();
        addComponentBody();
        addComponentFooter();

    }

    Font boldFont = new Font("Arial",Font.BOLD,18);
    Font bodyFont = new Font("Arial",Font.PLAIN,16);

    //  JPanel
    JPanel headerPanel;
    JPanel bodyPanel;
    JPanel footerPanel;
    JPanel bodyNorthPanel;
    JPanel bodyCenterPanel;
    JPanel bodySouthPanel;
    JPanel bodyNorthInside;
    JPanel bodySouthInside;
    JPanel topLeftPanel;
    JPanel topRightPanel;
    JPanel middlePanel;
    JPanel bottomPanel;
    JPanel bottomLeft;
    JPanel bottomDown;

    //    JLabel
    JLabel selectCategoryLabel;
    JLabel detailsLabel;
    JLabel productIdLabel;
    JLabel categoryLabel;
    JLabel nameLabel;
    JLabel sizeLabel;
    JLabel colourLabel;
    JLabel itemAvailableLabel;

    //    JButton
    JButton cart;
    JButton addToCartBtn;

    //  Other
    String[] category;
    JComboBox<String> comboBox;
    JTable table;
    JScrollPane scrollPane;
    TableModel model;
    GridBagConstraints constraints = new GridBagConstraints();

    void addLayouts(){
        setLayout(new BorderLayout());
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(10,50,10,50));
        headerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        headerPanel.setBackground(Color.blue);

        bodyPanel = new JPanel(new BorderLayout(0,10));
        bodyPanel.setBorder(BorderFactory.createEmptyBorder(5,50,5,50));
        bodyPanel.setBackground(Color.CYAN);
        bodyNorthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bodyNorthPanel.setBorder(BorderFactory.createEmptyBorder(10,50,65,0));
        bodyNorthPanel.setBackground(Color.GREEN);
        bodyNorthInside = new JPanel(new GridLayout(1,2,30,10));
        bodyNorthInside.setBackground(Color.red);
        bodyCenterPanel = new JPanel(new GridLayout(1,1));
        bodyCenterPanel.setBackground(Color.YELLOW);
        bodySouthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bodySouthPanel.setBorder(BorderFactory.createEmptyBorder(5,30,5,0));
        bodySouthPanel.setBackground(Color.magenta);
        bodySouthInside = new JPanel(new GridLayout(7,1,10,10));
        bodySouthPanel.setBackground(Color.pink);


        bodyPanel.add(bodyNorthPanel, BorderLayout.NORTH);
        bodyNorthPanel.add(bodyNorthInside);
        bodyPanel.add(bodyCenterPanel, BorderLayout.CENTER);
        bodyPanel.add(bodySouthPanel, BorderLayout.SOUTH);
        bodySouthPanel.add(bodySouthInside);

        footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        add(headerPanel,BorderLayout.NORTH);

        add(bodyPanel,BorderLayout.CENTER);

        add(footerPanel,BorderLayout.SOUTH);

    }

    void addComponentHead(){
        selectCategoryLabel = new JLabel("Select Product Category");
        category = new String[]{"All", "Electronics", "Clothing"};
        comboBox = new JComboBox<>(category);
        cart = new JButton("Shopping Cart");
        cart.setPreferredSize(new Dimension(200,40));
        cart.setFocusPainted(false);
        cart.setToolTipText("Go to Cart");

        headerPanel.add(cart);

        selectCategoryLabel.setFont(bodyFont);
        comboBox.setFont(bodyFont);
        bodyNorthInside.add(selectCategoryLabel);
        bodyNorthInside.add(comboBox);

    }

    void addComponentBody(){

        table = new JTable(spCtrl.productTableEditor);
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800,300));

        table.setFont(new Font("Arial",Font.PLAIN,16));
        table.setGridColor(Color.black);
        table.getTableHeader().setFont(boldFont);
        table.setRowHeight(30);

        bodyCenterPanel.add(scrollPane);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowSorter(spCtrl.tableSorter);

        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);


        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

    }



    void addComponentFooter(){
        addToCartBtn = new JButton("Add to Shopping Cart");
        addToCartBtn.setFont(boldFont);

        detailsLabel = new JLabel("Selected Product - Details");
        detailsLabel.setFont(boldFont);
        productIdLabel = new JLabel("Product Id: " );
        productIdLabel.setFont(boldFont);
        categoryLabel = new JLabel("Category: ");
        categoryLabel.setFont(boldFont);
        nameLabel = new JLabel("Name: ");
        nameLabel.setFont(boldFont);
        sizeLabel = new JLabel("Size: ");
        sizeLabel.setFont(boldFont);
        colourLabel = new JLabel("Colour: ");
        colourLabel.setFont(boldFont);
        itemAvailableLabel = new JLabel("Item available: ");
        itemAvailableLabel.setFont(boldFont);


//        bottomLeft.add(Box.createRigidArea(new Dimension(100,0)));
        bodySouthInside.add(detailsLabel);
//        bottomLeft.add(Box.createRigidArea(new Dimension(0,20)));
        bodySouthInside.add(productIdLabel);
//        bottomLeft.add(Box.createVerticalGlue());
        bodySouthInside.add(categoryLabel);
//        bottomLeft.add(Box.createVerticalGlue());
        bodySouthInside.add(nameLabel);
//        bottomLeft.add(Box.createVerticalGlue());
        bodySouthInside.add(sizeLabel);
//        bottomLeft.add(Box.createVerticalGlue());
        bodySouthInside.add(colourLabel);
//        bottomLeft.add(Box.createVerticalGlue());
        bodySouthInside.add(itemAvailableLabel);

//        bottomDown.add(addToCartBtn);
        footerPanel.add(addToCartBtn);
    }


//    public ShoppingCentreView(WestminsterShoppingManager uowShoppingManager) {
//        WestminsterShoppingManager shoppingManager = uowShoppingManager;
//
//        setLayout(new GridBagLayout());
//        GridBagConstraints constraints = new GridBagConstraints();
//        constraints.weightx = 1; //will stretch out the grid to 'x' axis
//        constraints.weighty = 1;
//
////        1s Row of main container
////        JButton j1 = new JButton("1st");
//        JPanel topLeftPanel = new JPanel();
//        JPanel topRightPanel = new JPanel();
//        topLeftPanel.setBackground(Color.red);
//        topRightPanel.setBackground(Color.green);
//        JLabel lable1 = new JLabel("Select Product Category");
//        String[] category = {"All", "Electronics", "Clothing"};
//        JComboBox<String> comboBox = new JComboBox<>(category);
//        JButton cart = new JButton("Shopping Cart");
//
//        topLeftPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,150,25));
//        topLeftPanel.add(lable1);
////        lable1.setBounds(50,50,200,20)
//        lable1.setFont(new Font("Arial",Font.PLAIN, 18));
//        topLeftPanel.add(comboBox);
////        comboBox.setBounds(300,50,120,30);
//        comboBox.setFont(new Font("Arial",Font.PLAIN, 18));
//        topRightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,30,20));
//        topRightPanel.add(cart);
//        cart.setFont(new Font("Arial",Font.BOLD,18));
//        cart.setVerticalTextPosition(SwingConstants.CENTER);
//
//        constraints.fill = GridBagConstraints.BOTH;
//        constraints.gridy = 0;
//        add(topLeftPanel,constraints);
//        add(topRightPanel,constraints);
//
////-----------------------------------------------------------------------------------------------
//
////        2nd Row of the main container
////        JButton j2 = new JButton("2nd");
//        JPanel middlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        middlePanel.setBackground(Color.yellow);
//        JTable table = new JTable();
//        JScrollPane scrollPane = new JScrollPane(table);
//        scrollPane.setPreferredSize(new Dimension(1200,300));
//        String[] columnNames = {"ProductID","Name","Category","Price","Info"};
//        String[][] data = {{"2jadjaw","ARAPAIMA","CLOTHING","8999.00","Medium, Black"},{"2jadjaw","ARAPAIMA","CLOTHING","8999.00","Medium, Black"},
//                {"2jadjaw","ARAPAIMA","CLOTHING","8999.00","Medium, Black"},{"2jadjaw","ARAPAIMA","CLOTHING","8999.00","Medium, Black"},
//                {"2jadjaw","ARAPAIMA","CLOTHING","8999.00","Medium, Black"},{"2jadjaw","ARAPAIMA","CLOTHING","8999.00","Medium, Black"},
//                {"2jadjaw","ARAPAIMA","CLOTHING","8999.00","Medium, Black"},{"2jadjaw","ARAPAIMA","CLOTHING","8999.00","Medium, Black"},
//                {"2jadjaw","ARAPAIMA","CLOTHING","8999.00","Medium, Black"},{"2jadjaw","ARAPAIMA","CLOTHING","8999.00","Medium, Black"},
//                {"2jadjaw","ARAPAIMA","CLOTHING","8999.00","Medium, Black"},{"2jadjaw","ARAPAIMA","CLOTHING","8999.00","Medium, Black"},
//                {"2jadjaw","ARAPAIMA","CLOTHING","8999.00","Medium, Black"},{"2jadjaw","ARAPAIMA","CLOTHING","8999.00","Medium, Black"},
//                {"2jadjaw","ARAPAIMA","CLOTHING","8999.00","Medium, Black"},{"2jadjaw","ARAPAIMA","CLOTHING","8999.00","Medium, Black"}};
////        String[][] data = new String[shoppingManager.productCount][5];
//
////        int count = 0;
////        for (Product product: shoppingManager.productList){
////            data[count][0] = product.getProductID();
////            data[count][1] = product.getProductName();
////            data[count][2] = product.getClass().getName();
////            data[count][3] = String.valueOf(product.getPrice());
////            if (product.getClass().getName().equals("Electronics")){
////                data[count][4] = ((Electronics)product).getBrand() + ", "+ ((Electronics) product).getWarrantyPeriod() + " Weeks Warranty";
////            }else{
////                data[count][4] = ((Clothing)product).getSize() + ", " + String.valueOf(((Clothing) product).getColour());
////            }
////            count++;
////        }
//
//        table.setFont(new Font("Arial",Font.PLAIN,16));
//        table.setGridColor(Color.black);
//        table.getTableHeader().setFont(new Font("Arial",Font.BOLD,18));
//        table.setRowHeight(30);
//
//        TableModel model = new DefaultTableModel(data,columnNames);
//        table.setModel(model);
//
//
//        constraints.fill = GridBagConstraints.BOTH;
//        constraints.gridy = 1;
//        constraints.gridwidth = 2;
//
//        add(middlePanel,constraints);
//        middlePanel.add(scrollPane);
//
//
//
////  -------------------------------------------------------------------------------------
////        3rd Row of the main container
////        JButton j3 = new JButton("3rd");
//        JPanel bottomPanel = new JPanel(new BorderLayout());
//        bottomPanel.setBackground(Color.blue);
//        JPanel bottomLeft = new JPanel();
//        bottomLeft.setLayout(new BoxLayout(bottomLeft,BoxLayout.Y_AXIS));
//        bottomLeft.setBackground(Color.red);
//        JPanel bottomDown = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        bottomDown.setBackground(Color.yellow);
//
//        JButton addToCartBtn = new JButton("Add to Shopping Cart");
//        addToCartBtn.setFont(new Font("Arial",Font.BOLD,18));
//
//        bottomPanel.add(bottomLeft, BorderLayout.CENTER);
//        bottomPanel.add(bottomDown, BorderLayout.PAGE_END);
//
//        JLabel detailsLabel = new JLabel("Selected Product - Details");
//        detailsLabel.setFont(new Font("Arial",Font.BOLD, 18));
//        JLabel productIdLabel = new JLabel("Product Id: " );
//        productIdLabel.setFont(new Font("Arial",Font.PLAIN, 18));
//        JLabel categoryLabel = new JLabel("Category: ");
//        categoryLabel.setFont(new Font("Arial",Font.PLAIN, 18));
//        JLabel nameLabel = new JLabel("Name: ");
//        nameLabel.setFont(new Font("Arial",Font.PLAIN, 18));
//        JLabel sizeLabel = new JLabel("Size: ");
//        sizeLabel.setFont(new Font("Arial",Font.PLAIN, 18));
//        JLabel colourLabel = new JLabel("Colour: ");
//        colourLabel.setFont(new Font("Arial",Font.PLAIN, 18));
//        JLabel itemAvailableLabel = new JLabel("Item available: ");
//        itemAvailableLabel.setFont(new Font("Arial",Font.PLAIN, 18));
//
//
//        bottomLeft.add(Box.createRigidArea(new Dimension(100,0)));
//        bottomLeft.add(detailsLabel);
//        bottomLeft.add(Box.createRigidArea(new Dimension(0,20)));
//        bottomLeft.add(productIdLabel);
//        bottomLeft.add(Box.createVerticalGlue());
//        bottomLeft.add(categoryLabel);
//        bottomLeft.add(Box.createVerticalGlue());
//        bottomLeft.add(nameLabel);
//        bottomLeft.add(Box.createVerticalGlue());
//        bottomLeft.add(sizeLabel);
//        bottomLeft.add(Box.createVerticalGlue());
//        bottomLeft.add(colourLabel);
//        bottomLeft.add(Box.createVerticalGlue());
//        bottomLeft.add(itemAvailableLabel);
//
//        bottomDown.add(addToCartBtn);
//
//        constraints.fill = GridBagConstraints.BOTH;
//        constraints.gridy = 2;
//        add(bottomPanel,constraints);
//    }
}
