package com.practiceUni.shoppingWeb.service.impl;

import com.practiceUni.shoppingWeb.dao.ProductDAO;
import com.practiceUni.shoppingWeb.domain.Product;
import com.practiceUni.shoppingWeb.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private static  final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductDAO productDAO;

    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public Product createProduct(Product product) {
        return productDAO.create(product);
    }

    @Override
    public Product updateProduct(Product product) {
        if(product.getId() == null){
            LOGGER.error("Failed to update the user due to null Id");
            return null;
        } else {
      return productDAO.update(product);
    }
  }

  @Override
  public boolean deleteProductById(Integer id) {
        if (id == null){
        return false;
        }
        else {
            return productDAO.deleteById(id);
        }
    }

  @Override
  public Product findProductById(Integer id) {
        if(id == null){
            return null;
        }

    Product product = productDAO.findById(id);

    if (product == null) {
      LOGGER.error("Failed to find a product due to wrong Id: " + id);
      return null;
    } else {
      return product;
    }
  }

    @Override
    public Product findProductByName(String name) {
        if(name == null){
            return null;
        }
        Product product = productDAO.findByName(name);

        if(product == null){
            LOGGER.error("Failed to find a product due to wrong name: " + name);
            return null;
        } else {
        return product;
     }
    }
}
