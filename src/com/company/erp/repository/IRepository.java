package com.company.erp.repository;

import java.util.List;

/**
 * Veri erişim operasyonlarının standart olmasını sağlar.
 */
public interface IRepository<T> {

    // Yeni kayıt ekler
    void add(T entity);

    // ID ile tek kayıt getirir
    T getById(int id);

    // Tüm kayıtları listeler
    List<T> getAll();
}