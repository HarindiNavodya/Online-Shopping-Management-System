import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WestminsterFrame {

    public static ShoppingCart usersShoppingCart = new ShoppingCart();

    public JFrame westminsterFrame = new JFrame("Westminster Shopping Center");

    public static JPanel topPanel, bottomPanel, vismalPanel,
            vismalPanel1, vismalPanel2, vismalPanel3,
            bottomPanel1, bottomPanel2;

    public static JTable table;

    public static JScrollPane scrollPane;

    public static String dropdownOption;

    public static Product selectedProduct;

    public static JLabel idLabel, categoryLabel, nameLabel, availItemsLabel, extraLabel1,extraLabel2;

    public WestminsterFrame() {
        westminsterFrame.setSize(800, 700);
        westminsterFrame.setLayout(new GridLayout(2, 1));

        topPanel = new JPanel(new GridLayout(2, 1));
        bottomPanel = new JPanel(new GridLayout(1, 2));
        vismalPanel = new JPanel(new GridLayout(1, 3));

        vismalPanel1 = new JPanel(new GridBagLayout());
        vismalPanel2 = new JPanel(new GridBagLayout());
        vismalPanel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        bottomPanel1 = new JPanel();
        bottomPanel2 = new JPanel();

        bottomPanel1.setLayout(new BoxLayout(bottomPanel1, BoxLayout.Y_AXIS));

        int marginSize = 7;
        EmptyBorder emptyBorder = new EmptyBorder(marginSize, marginSize*4, marginSize, 0);




        idLabel = new JLabel("Product ID -");
        nameLabel = new JLabel("Name - ");
        categoryLabel = new JLabel("category -");
        availItemsLabel = new JLabel("available label - ");
        extraLabel1 = new JLabel("Size");
        extraLabel2 = new JLabel("Color - ");

        bottomPanel1.setBorder(emptyBorder);
        idLabel.setBorder(emptyBorder);
        nameLabel.setBorder(emptyBorder);
        categoryLabel.setBorder(emptyBorder);
        availItemsLabel.setBorder(emptyBorder);
        extraLabel1.setBorder(emptyBorder);
        extraLabel2.setBorder(emptyBorder);


        JLabel topPanelText = new JLabel("Select Product Category");
        JLabel bottomPanelText = new JLabel("Select Product - Details");

        String[] dropdownList = {"All", "Electronic", "Clothing"};
        JComboBox<String> dropdownMenu = new JComboBox<>(dropdownList);
        dropdownMenu.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXX");

        dropdownOption = (String) dropdownMenu.getSelectedItem();


        JButton shoppingCartButton = new JButton("Shopping Cart");
        JButton addToCartButton = new JButton(" Add to Shopping Cart");
        bottomPanel2.add(addToCartButton);

        vismalPanel1.add(topPanelText);
        vismalPanel2.add(dropdownMenu);
        vismalPanel3.add(shoppingCartButton);

        vismalPanel.add(vismalPanel1);
        vismalPanel.add(vismalPanel2);
        vismalPanel.add(vismalPanel3);

        table = createTable(WestminsterShoppingManager.productList.getProductList());
        scrollPane = new JScrollPane((table));

        dropdownMenu.addActionListener(e -> {
            dropdownOption = (String) dropdownMenu.getSelectedItem();
            System.out.println(dropdownOption);
            updateTableModel();
        });

        topPanel.add(vismalPanel);
        topPanel.add(scrollPane);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    System.out.println("item selected " + selectedRow);
                    if (selectedRow != -1) {
                        int modelRow = table.convertRowIndexToModel(selectedRow);
                        selectedProduct = getProductList(WestminsterShoppingManager.productList.getProductList(), dropdownOption).get(modelRow);
//                        System.out.println(selectedProduct.displayProduct());
                        updateDisplayPanel(selectedProduct);
                    }
                }

            }
        } );

        shoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShoppingCartFrame();
            }
        } );

        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println(selectedProduct.displayProduct());
                if (selectedProduct != null) {
                    System.out.println(selectedProduct.displayProduct());
                    usersShoppingCart.addProduct(selectedProduct);
                }
            }
        });

        bottomPanel1.add(bottomPanelText);
        bottomPanel1.add(idLabel);
        bottomPanel1.add(categoryLabel);
        bottomPanel1.add(nameLabel);
        bottomPanel1.add(extraLabel1);
        bottomPanel1.add(extraLabel2);
        bottomPanel1.add(availItemsLabel);


        bottomPanel.add(bottomPanel1);
        bottomPanel.add(bottomPanel2);

        westminsterFrame.add(topPanel);
        westminsterFrame.add(bottomPanel);

        westminsterFrame.setVisible(true);
        westminsterFrame.setDefaultCloseOperation(westminsterFrame.EXIT_ON_CLOSE);

    }

    public static JTable createTable(ArrayList<Product> productList) {
        String[] columnNames = {"Product ID", "Name", "Category", "Price ($)", "Info"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Product product : productList) {
            System.out.println(product.displayProduct());
            switch (product.getProductType()) {
                case "electronic":
                    ;
                    Object[] rowDataelectronic = {
                            product.getProductId(),
                            product.getProductName(),
                            product.getProductType(),
                            product.getPrice(),
                            product.getBrand() + ", " +
                                    product.getWarrantyPeriod() + "months warrenty"};
                    model.addRow(rowDataelectronic);

                    break;
                case "clothing":
                    ;
                    Object[] rowDataclothing = {
                            product.getProductId(),
                            product.getProductName(),
                            product.getProductType(),
                            product.getPrice(),
                            product.getSize() + ", " +
                                    product.getColor()};
                    model.addRow(rowDataclothing);
                    break;
            }
        }


        JTable table = new JTable(model);
        table.setDefaultEditor(Object.class, null);
        return table;
    }

    private void updateTableModel() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        ArrayList<Product> productList = getProductList(WestminsterShoppingManager.productList.getProductList(),dropdownOption);
        for (Product product : productList) {
            System.out.println(product.displayProduct());
            switch (product.getProductType()) {
                case "electronic":
                    ;
                    Object[] rowDataelectronic = {
                            product.getProductId(),
                            product.getProductName(),
                            product.getProductType(),
                            product.getPrice(),
                            product.getBrand() + ", " +
                                    product.getWarrantyPeriod() + "months warrenty"};
                    model.addRow(rowDataelectronic);

                    break;
                case "clothing":
                    ;
                    Object[] rowDataclothing = {
                            product.getProductId(),
                            product.getProductName(),
                            product.getProductType(),
                            product.getPrice(),
                            product.getSize() + ", " +
                                    product.getColor()};
                    model.addRow(rowDataclothing);
                    break;
            }
        }
    }

    public static void updateDisplayPanel(Product product) {
        System.out.println(selectedProduct.displayProduct());

        idLabel.setText("Product ID - "+ product.getProductId());
        nameLabel.setText("Name - "+ product.getProductName());
        categoryLabel.setText("Category - "+ product.getProductType());
        availItemsLabel.setText("Items available  - "+ product.getNoOfItems());

        switch (product.getProductType()){
            case "electronic":
                extraLabel1.setText("Brand - " + product.getBrand());
                extraLabel2.setText("Warranty Period - " + product.getWarrantyPeriod());
                break;
            case "clothing" :
                extraLabel1.setText("Brand - " + product.getSize());
                extraLabel2.setText("Colour - " + product.getColor());
                break;

        }


    }


    public static ArrayList<Product> getProductList(ArrayList<Product> productList, String dropdownOption) {
        ArrayList<Product> selectedProductList = new ArrayList<>();
        switch (dropdownOption) {
            case "All":
                selectedProductList = productList;
            break;
            case "Electronic":
                for (Product product : productList) {
                    if (product.getProductType().equals("electronic")) {
                        selectedProductList.add(product);
                    }
                }
            break;
            case "Clothing":
                for (Product product: productList) {
                    if (product.getProductType().equals("clothing")){
                        selectedProductList.add(product);
                    }
                }
        }
        return selectedProductList;

    }



}