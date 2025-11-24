package com.company.erp.model;

	import java.time.LocalDate;

	/**
	 * Order sınıfı bir müşterinin verdiği siparişi temsil eder.
	 * Bu sınıf analitik modülünde satış trendlerinin hesaplanmasında temel alınacaktır.
	 */
public class Order {

	   
	    private int id;
	    private int customerId;
	    private int productId;

	    // Sipariş edilen ürün adedi
	    private int quantity;

	    // Siparişin verildiği tarih
	    private LocalDate orderDate;

	    // Bu siparişe ait toplam tutar (quantity * product price)
	    private double totalPrice;

	    /**
	     * Yeni sipariş oluşturmak için kullanılır.
	     * Sipariş verilirken sistem içinde hesaplanarak kaydedilir.
	     */
	    public Order(int customerId, int productId, int quantity, LocalDate orderDate, double totalPrice) {
	        this.customerId = customerId;
	        this.productId = productId;
	        this.quantity = quantity;
	        this.orderDate = orderDate;
	        this.totalPrice = totalPrice;
	    }

	    /**
	     * Veritabanından okunan mevcut sipariş nesnesi için kullanılır.
	     */
	    public Order(int id, int customerId, int productId, int quantity, LocalDate orderDate, double totalPrice) {
	        this.id = id;
	        this.customerId = customerId;
	        this.productId = productId;
	        this.quantity = quantity;
	        this.orderDate = orderDate;
	        this.totalPrice = totalPrice;
	    }

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public int getCustomerId() {
	        return customerId;
	    }

	    public int getProductId() {
	        return productId;
	    }

	    public int getQuantity() {
	        return quantity;
	    }

	    public LocalDate getOrderDate() {
	        return orderDate;
	    }

	    public double getTotalPrice() {
	        return totalPrice;
	    }

	    /**
	     * Sipariş bilgilerini okunabilir şekilde döndürür.
	     * Debug ve loglama için kullanışlıdır.
	     */
	    @Override
	    public String toString() {
	        return "Sipariş -> ÜrünID: " + productId +
	               ", Adet: " + quantity +
	               ", Tarih: " + orderDate +
	               ", Toplam: " + totalPrice;
	    }
}
