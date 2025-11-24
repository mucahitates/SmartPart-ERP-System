package com.company.erp.util;


import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;

public class databaseConnection {
	
	
	/**
	 * MySQL bağlantı bilgilerini config.txt dosyasından okur
	 * ve Connection nesnesi üretir.
	 */
	    private static String url;
	    private static String user;
	    private static String password;

	    // Config dosyasını okuyan metot
	    private static void loadConfig() {
	        try (BufferedReader br = new BufferedReader(new FileReader("src/config.txt"))) {
	            br.lines().forEach(line -> {
	                if (line.startsWith("url=")) {
	                    url = line.replace("url=", "");
	                } else if (line.startsWith("user=")) {
	                    user = line.replace("user=", "");
	                } else if (line.startsWith("password=")) {
	                    password = line.replace("password=", "");
	                }
	            });
	        } catch (Exception e) {
	            System.out.println("Config dosyası okunamadı: " + e.getMessage());
	        }
	    }

	    /**
	     * Veritabanı bağlantısı döndürür
	     */
	    public static Connection getConnection() {
	        loadConfig();
	        Connection connection = null;

	        try {
	            connection = DriverManager.getConnection(url, user, password);
	        } catch (Exception e) {
	            System.out.println("Veritabanı bağlantı hatası: " + e.getMessage());
	        }

	        return connection;
	    }
}
