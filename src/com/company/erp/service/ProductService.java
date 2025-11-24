package com.company.erp.service;

import com.company.erp.model.Product;
import com.company.erp.repository.ProductRepository;

import java.util.List;

/**
 * ProductService, ürünlerle ilgili iş kurallarını kontrol eder.
 * Gerçek ERP mantığına uygun şekilde güncellenmiştir.
 */
public class ProductService {

    private ProductRepository productRepository;

    public ProductService() {
        this.productRepository = new ProductRepository();
    }

    /**
     * Yeni ürün eklerken iş kurallarını kontrol eder.
     */
    public void addProduct(Product product) {

        // Ürün adı boş olamaz
        if (product.getName() == null || product.getName().isEmpty()) {
            System.out.println("Ürün adı boş olamaz.");
            return;
        }

        // Ürün kodu boş olamaz
        if (product.getProductCode() == null || product.getProductCode().isEmpty()) {
            System.out.println("Ürün kodu boş olamaz.");
            return;
        }

        // Alış fiyatı negatif olamaz
        if (product.getPurchasePrice() <= 0) {
            System.out.println("Alış fiyatı 0'dan büyük olmalıdır.");
            return;
        }

        // Satış fiyatı alış fiyatından küçük olamaz
        if (product.getSalePrice() < product.getPurchasePrice()) {
            System.out.println("Satış fiyatı alış fiyatından küçük olamaz.");
            return;
        }

        // Stok negatif olamaz
        if (product.getStock() < 0) {
            System.out.println("Stok negatif olamaz.");
            return;
        }

        // Minimum stok negatif olamaz
        if (product.getMinStock() < 0) {
            System.out.println("Minimum stok negatif olamaz.");
            return;
        }

        // Tüm iş kuralları geçildi → veritabanına kaydet
        productRepository.add(product);
    }

    /**
     * ID'ye göre ürün getirir.
     */
    public Product getProductById(int id) {
        return productRepository.getById(id);
    }

    /**
     * Tüm ürünleri listeler.
     */
    public List<Product> getAllProducts() {
        return productRepository.getAll();
    }
}
