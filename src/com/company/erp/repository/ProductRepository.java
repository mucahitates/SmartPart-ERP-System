package com.company.erp.repository;

import com.company.erp.model.Product;
import com.company.erp.util.databaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * ProductRepository sınıfı, Product nesnesinin
 * veritabanı ile olan tüm işlemlerini yönetir.
 * Gerçek ERP yapısına uygun şekilde güncellenmiştir.
 */
public class ProductRepository implements IRepository<Product> {

    @Override
    public void add(Product product) {

        String sql = "INSERT INTO products (product_code, name, category, brand, purchase_price, sale_price, stock, min_stock, created_at) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
            Connection conn = databaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, product.getProductCode());
            ps.setString(2, product.getName());
            ps.setString(3, product.getCategory());
            ps.setString(4, product.getBrand());
            ps.setDouble(5, product.getPurchasePrice());
            ps.setDouble(6, product.getSalePrice());
            ps.setInt(7, product.getStock());
            ps.setInt(8, product.getMinStock());
            ps.setTimestamp(9, Timestamp.valueOf(product.getCreatedAt()));

            ps.executeUpdate();
            System.out.println("Ürün veritabanına eklendi.");

        } catch (Exception e) {
            System.out.println("Ürün ekleme hatası: " + e.getMessage());
        }
    }

    @Override
    public Product getById(int id) {

        Product product = null;
        String sql = "SELECT * FROM products WHERE id = ?";

        try (
            Connection conn = databaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();

                product = new Product(
                        rs.getInt("id"),
                        rs.getString("product_code"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getString("brand"),
                        rs.getDouble("purchase_price"),
                        rs.getDouble("sale_price"),
                        rs.getInt("stock"),
                        rs.getInt("min_stock"),
                        createdAt
                );
            }

        } catch (Exception e) {
            System.out.println("Ürün getirme hatası: " + e.getMessage());
        }

        return product;
    }

    @Override
    public List<Product> getAll() {

        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (
            Connection conn = databaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();

                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("product_code"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getString("brand"),
                        rs.getDouble("purchase_price"),
                        rs.getDouble("sale_price"),
                        rs.getInt("stock"),
                        rs.getInt("min_stock"),
                        createdAt
                );

                productList.add(product);
            }

        } catch (Exception e) {
            System.out.println("Ürün listeleme hatası: " + e.getMessage());
        }

        return productList;
    }
}
