package service;

import model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    void create(Product customer);

    Product findById(int id);

    void edit(int id, Product customer);

    void delete(int id);

    List<Product> findByName(String name);
}
