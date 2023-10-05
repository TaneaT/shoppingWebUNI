package com.practiceUni.shoppingWeb.dao.impl;

import com.practiceUni.shoppingWeb.JdbcConnection;
import com.practiceUni.shoppingWeb.dao.ProductDAO;
import com.practiceUni.shoppingWeb.domain.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductDAOImplTest {

  @Autowired private ProductDAO productDAO;

  private Product getTestProduct() {
    return new Product("Name","category","size", "color",1);
  }

  @AfterEach
  void tearDown() {

    String brandProductSql = "DELETE FROM brands_product";
    String purchaseSql = "DELETE FROM purchase";
    String productSql = "DELETE FROM products";

    try (Connection conn = JdbcConnection.getConnection();
         PreparedStatement brandProduct = conn.prepareStatement(brandProductSql);
         PreparedStatement purchase = conn.prepareStatement(purchaseSql);
         PreparedStatement products = conn.prepareStatement(productSql) ) {

      brandProduct.executeUpdate();
      purchase.executeUpdate();
      products.executeUpdate();

    } catch (SQLException e) {
      e.getStackTrace();
    }
  }

  @Test
  void shouldCreateProduct() {
    Product product = getTestProduct();
    assertNotNull(productDAO.create(product));

    Product testProduct = productDAO.findById(product.getId());
    assertNotNull(testProduct);
    assertEquals(testProduct, product);


  }

  @Test
  void shouldUpdateProduct() {
    Product product = getTestProduct();
    assertNotNull(productDAO.create(product));

    product.setQuantity(2);

    Product updatedProduct = productDAO.update(product);
    assertNotNull(updatedProduct);

    Product testProduct = productDAO.findById(updatedProduct.getId());
    assertNotNull(testProduct);
    assertEquals(testProduct, updatedProduct);


  }

  @Test
  void shouldCreateProductIfNotFound() {
    Product product = getTestProduct();

    assertNull(productDAO.findById(product.getId()));

    assertNotNull(productDAO.create(product));

    Product testProduct = productDAO.findById(product.getId());
    assertNotNull(testProduct);
    assertEquals(testProduct, product);


  }

  @Test
  void shouldDeleteProductById() {
    Product product = getTestProduct();
    assertNotNull(productDAO.create(product));

    assertTrue(productDAO.deleteById(product.getId()));
  }

  @Test
  void shouldFindProductById() {
    Product product = getTestProduct();

    assertNotNull(productDAO.create(product));

    Product foundProduct = productDAO.findById(product.getId());
    assertNotNull(foundProduct);
    assertEquals(foundProduct, product);


  }

  @Test
  void shouldFindProductByName() {
    Product product = getTestProduct();
    assertNotNull(productDAO.create(product));

    Product foundProduct = productDAO.findByName(product.getName());
    assertNotNull(foundProduct);
    assertEquals(foundProduct, product);


  }

  @Test
  void shouldGetAllProducts() {
    Product product1 = new Product("Name","category","size", "color",1);
    Product product2 = new Product("Name","category","size", "color",2);

    assertNotNull(productDAO.create(product1));
    assertNotNull(productDAO.create(product2));

    List<Product> productList = new ArrayList<>();
    productList.add(product1);
    productList.add(product2);

    assertEquals(productList.size(), 2);

    List<Product> anotherList = productDAO.getAllProducts();

    assertNotNull(anotherList);
    assertEquals(anotherList.size(), productList.size());

  }
}
