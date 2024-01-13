package com.OOPCW.atheek;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class ShoppingCentreView extends JFrame{

    ShoppingCentreController spCtrl;

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


    /**Create main layout to the Frame
     * setting up JPanels and layout managers*/
    void addLayouts(){
        setLayout(new BorderLayout());
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(10,50,10,50));
        headerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        bodyPanel = new JPanel(new BorderLayout(0,10));
        bodyPanel.setBorder(BorderFactory.createEmptyBorder(5,50,5,50));

        bodyNorthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bodyNorthPanel.setBorder(BorderFactory.createEmptyBorder(10,50,65,0));

        bodyNorthInside = new JPanel(new GridLayout(1,2,30,10));

        bodyCenterPanel = new JPanel(new GridLayout(1,1));

        bodySouthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bodySouthPanel.setBorder(BorderFactory.createEmptyBorder(5,30,5,0));

        bodySouthInside = new JPanel(new GridLayout(7,1,10,10));


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

    /**Adding Components to Top part of the layouts*/
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

//    @Override
//            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
//                Component comp = super.prepareRenderer(renderer,row,column);
//                Color redColor = new Color(250,0,0);
//                Color df = table.getBackground();
//                Product product = spCtrl.uowModel.productList.get(row);
//                if (product.getNoOfItems() < 3){
//                    comp.setBackground(redColor);
//                }else {
//                    comp.setBackground(df);
//                }
//                return comp;
//            }

    /**Adding components to the body part of layouts*/
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



    /** Adding components to the bottom part of the Layouts*/
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

        bodySouthInside.add(detailsLabel);
        bodySouthInside.add(productIdLabel);
        bodySouthInside.add(categoryLabel);
        bodySouthInside.add(nameLabel);
        bodySouthInside.add(sizeLabel);
        bodySouthInside.add(colourLabel);
        bodySouthInside.add(itemAvailableLabel);
        footerPanel.add(addToCartBtn);
    }


}
