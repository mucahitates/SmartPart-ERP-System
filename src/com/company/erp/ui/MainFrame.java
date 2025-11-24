package com.company.erp.ui;

import com.company.erp.model.Product;
import com.company.erp.service.ProductService;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JTextField txtProductCode;
    private JTextField txtName;
    private JTextField txtCategory;
    private JTextField txtBrand;
    private JTextField txtPurchasePrice;
    private JTextField txtSalePrice;
    private JTextField txtStock;
    private JTextField txtMinStock;

    private ProductService productService;

    public MainFrame() {
        setTitle("SmartPart ERP - Ürün Yönetimi");
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        productService = new ProductService();

        initUI();
    }

    private void initUI() {

        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(25, 118, 210));
        headerPanel.setPreferredSize(new Dimension(800, 70));

        JLabel lblTitle = new JLabel("SmartPart ERP - Profesyonel Ürün Giriş Paneli");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        headerPanel.add(lblTitle);

        add(headerPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(25, 40, 25, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Satır 1 - Ürün Kodu
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Ürün Kodu:"), gbc);
        gbc.gridx = 1;
        txtProductCode = new JTextField();
        formPanel.add(txtProductCode, gbc);

        // Satır 2 - Ürün Adı
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Ürün Adı:"), gbc);
        gbc.gridx = 1;
        txtName = new JTextField();
        formPanel.add(txtName, gbc);

        // Satır 3 - Kategori
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Kategori:"), gbc);
        gbc.gridx = 1;
        txtCategory = new JTextField();
        formPanel.add(txtCategory, gbc);

        // Satır 4 - Marka
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Marka:"), gbc);
        gbc.gridx = 1;
        txtBrand = new JTextField();
        formPanel.add(txtBrand, gbc);

        // Satır 5 - Alış Fiyatı
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Alış Fiyatı (₺):"), gbc);
        gbc.gridx = 1;
        txtPurchasePrice = new JTextField();
        formPanel.add(txtPurchasePrice, gbc);

        // Satır 6 - Satış Fiyatı
        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(new JLabel("Satış Fiyatı (₺):"), gbc);
        gbc.gridx = 1;
        txtSalePrice = new JTextField();
        formPanel.add(txtSalePrice, gbc);

        // Satır 7 - Stok
        gbc.gridx = 0; gbc.gridy = 6;
        formPanel.add(new JLabel("Stok Miktarı:"), gbc);
        gbc.gridx = 1;
        txtStock = new JTextField();
        formPanel.add(txtStock, gbc);

        // Satır 8 - Minimum Stok
        gbc.gridx = 0; gbc.gridy = 7;
        formPanel.add(new JLabel("Minimum Stok:"), gbc);
        gbc.gridx = 1;
        txtMinStock = new JTextField();
        formPanel.add(txtMinStock, gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.EAST;

        JButton btnAdd = new JButton("Ürün Kaydet");
        btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnAdd.setBackground(new Color(46, 125, 50));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFocusPainted(false);
        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnAdd.addActionListener(e -> addProduct());

        formPanel.add(btnAdd, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    private void addProduct() {
        try {
            Product product = new Product(
                txtProductCode.getText(),
                txtName.getText(),
                txtCategory.getText(),
                txtBrand.getText(),
                Double.parseDouble(txtPurchasePrice.getText()),
                Double.parseDouble(txtSalePrice.getText()),
                Integer.parseInt(txtStock.getText()),
                Integer.parseInt(txtMinStock.getText())
            );

            productService.addProduct(product);

            JOptionPane.showMessageDialog(this,
                    "Ürün başarıyla kaydedildi!",
                    "Başarılı",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Lütfen tüm alanları doğru doldurun.",
                    "Hata",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
