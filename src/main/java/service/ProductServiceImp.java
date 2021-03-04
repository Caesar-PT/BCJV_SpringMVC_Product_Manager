package service;

import model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceImp implements ProductService{
    private static Map<Integer, Product> products;

    static {
        products = new HashMap<>();
        products.put(1, (new Product(1, "sp1", 10, 100, "des1")));
        products.put(2, (new Product(2, "sp2", 11, 101, "des2")));
        products.put(3, (new Product(3, "sp3", 12, 102, "des3")));
        products.put(4, (new Product(4, "sp4", 13, 103, "des4")));
        products.put(5, (new Product(5, "sp5", 14, 104, "des5")));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void create(Product customer) {
        int key = (int) Math.round(Math.random()*1000000);
        customer.setId(key);
        products.put(key, customer);
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void edit(int id, Product customer) {
        products.put(id, customer);
    }

    @Override
    public void delete(int id) {
        products.remove(id);
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> productList = new ArrayList<>(products.values());
        List<Product> findResult = new ArrayList<>();
        for (Product p: productList) {
            if (p.getName().equalsIgnoreCase(name)){
                findResult.add(p);
            }
        }
        return findResult;
    }
}
