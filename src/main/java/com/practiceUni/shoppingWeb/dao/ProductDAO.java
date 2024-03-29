package com.practiceUni.shoppingWeb.dao;

import com.practiceUni.shoppingWeb.domain.Product;

import java.util.List;

public interface ProductDAO extends DAO<Product> {

  Product create(Product product);

  Product update(Product product);

  boolean deleteById(Integer id);

  Product findById(Integer id);

  Product findByName(String name);

  List<Product> findByCategory (String category);

  List<Product> getAllProducts();
}
