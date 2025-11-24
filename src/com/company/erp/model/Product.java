package com.company.erp.model;

import java.time.LocalDateTime;

/**
 * ERP sistemindeki ürünleri temsil eden entity sınıfı.
 * Gerçek ERP standartlarına uygun şekilde genişletilmiştir.
 */
public class Product {

    private int id;
    private String productCode;
    private String name;
    private String category;
    private String brand;

    private double purchasePrice;   // Alış fiyatı
    private double salePrice;       // Satış fiyatı

    private int stock;
    private int minStock;

    private LocalDateTime createdAt;

    /**
     * Yeni ürün oluşturmak için kullanılır (veritabanına kaydedilmeden önce)
     */
    public Product(String productCode, String name, String category, String brand,
                   double purchasePrice, double salePrice,
                   int stock, int minStock) {

        this.productCode = productCode;
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.stock = stock;
        this.minStock = minStock;
        this.createdAt = LocalDateTime.now();
    }

    /**
     * Veritabanından gelen ürünler için kullanılan constructor
     */
    public Product(int id, String productCode, String name, String category, String brand,
                   double purchasePrice, double salePrice,
                   int stock, int minStock, LocalDateTime createdAt) {

        this.id = id;
        this.productCode = productCode;
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.stock = stock;
        this.minStock = minStock;
        this.createdAt = createdAt;
    }

    // ================= GETTER & SETTER =================

    public int getId() { return id; }
    public String getProductCode() { return productCode; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getBrand() { return brand; }
    public double getPurchasePrice() { return purchasePrice; }
    public double getSalePrice() { return salePrice; }
    public int getStock() { return stock; }
    public int getMinStock() { return minStock; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Stok kritik seviyede mi kontrol eder
     */
    public boolean isStockCritical() {
        return stock <= minStock;
    }

    /**
     * Kâr hesaplama
     */
    public double getProfitPerUnit() {
        return salePrice - purchasePrice;
    }

    @Override
    public String toString() {
        return name +
                " | Kod: " + productCode +
                " | Stok: " + stock +
                " | Satış: " + salePrice +
                " | Kategori: " + category +
                " | Marka: " + brand;
    }
}
