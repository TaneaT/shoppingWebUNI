package com.practiceUni.shoppingWeb.service;

import com.practiceUni.shoppingWeb.domain.Product;

public interface ProductService {

  Product createProduct(Product product);

  Product updateProduct(Product product);

  boolean deleteProductById(Integer id);

  Product findProductById(Integer id);

  Product findProductByName(String name);
}
