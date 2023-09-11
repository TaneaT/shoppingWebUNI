package com.practiceUni.shoppingWeb.dao.impl;

import com.practiceUni.shoppingWeb.JdbcConnection;
import com.practiceUni.shoppingWeb.dao.ProductDAO;
import com.practiceUni.shoppingWeb.domain.Brand;
import com.practiceUni.shoppingWeb.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ProductDAOImpl implements ProductDAO {

  private static final Logger LOGGER = LoggerFactory.getLogger(ProductDAOImpl.class);

  @Override
  public Product create(Product product) {
    String sql =
        "INSERT INTO products(product_name, product_size, product_color, product_quantity) VALUES (?,?,?,?)";

    try (Connection conn = JdbcConnection.getConnection();
        PreparedStatement createProduct =
            conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

      createProduct.setString(1, product.getName());
      createProduct.setString(2, product.getSize());
      createProduct.setString(3, product.getColor());
      createProduct.setInt(4, product.getQuantity());

      int insertedRows = createProduct.executeUpdate();

      if (insertedRows > 0) {
        ResultSet generatedkeys = createProduct.getGeneratedKeys();

        if (generatedkeys.next()) {
          int generatedId = generatedkeys.getInt(1);
          product.setId(generatedId);
        }
      }
    } catch (SQLException e) {
      LOGGER.error("Failed to create a product " + e);
    }

    return product;
  }

  @Override
  public Product update(Product product) {
    String sql = "UPDATE products SET product_quantity = ? WHERE product_id = ?";

    try (Connection conn = JdbcConnection.getConnection();
        PreparedStatement updateProduct = conn.prepareStatement(sql)) {

      updateProduct.setInt(1, product.getQuantity());
      updateProduct.setInt(2, product.getId());

      updateProduct.executeUpdate();

    } catch (SQLException e) {
      LOGGER.error("Failed to update the product with id " + product.getId() + e);
    }

    return product;
  }

  @Override
  public boolean deleteById(Integer id) {
    String sql = "DELETE FROM products WHERE product_id = ?";

    try (Connection conn = JdbcConnection.getConnection();
        PreparedStatement deleteProduct = conn.prepareStatement(sql)) {

      deleteProduct.setInt(1, id);

      deleteProduct.executeUpdate();

    } catch (SQLException e) {
      LOGGER.error("Failed to delete the product with id: " + id + e);
    }

    return true;
  }

  @Override
  public Product findById(Integer id) {

    String sql = "SELECT * FROM products WHERE products.product_id = ?";
    Product product = new Product();

    try (Connection conn = JdbcConnection.getConnection();
        PreparedStatement findProduct = conn.prepareStatement(sql)) {

      if (id != null) {
        findProduct.setInt(1, id);
      } else {
        return null;
      }

      ResultSet resultSet = findProduct.executeQuery();

      while (resultSet.next()) {
        Integer productId = resultSet.getInt("product_id");
        String name = resultSet.getString("product_name");
        String size = resultSet.getString("product_size");
        String color = resultSet.getString("product_color");
        Integer quantity = resultSet.getInt("product_quantity");

        product = new Product(productId, name, size, color, quantity);
      }

    } catch (SQLException e) {
      LOGGER.error("Failed to find a product with id: " + id + e);
    }

    return product;
  }

  @Override
  public Product findByName(String name) {
    String sql = "SELECT * FROM products WHERE products.product_name = ?";

    Product product = new Product();

    try (Connection conn = JdbcConnection.getConnection();
        PreparedStatement findProduct = conn.prepareStatement(sql)) {

      if (name != null) {
        findProduct.setString(1, name);
      } else {
        return null;
      }

      ResultSet resultSet = findProduct.executeQuery();

      while (resultSet.next()) {
        Integer Id = resultSet.getInt("product_id");
        String productName = resultSet.getString("product_name");
        String size = resultSet.getString("product_size");
        String color = resultSet.getString("product_color");
        Integer quantity = resultSet.getInt("product_quantity");

        product = new Product(Id, productName, size, color, quantity);
      }

    } catch (SQLException e) {
      LOGGER.error("Failed to find a product with name : " + name + e);
    }

    return product;
  }

  @Override
  public List<Product> getAllProducts() {
    String sql = "SELECT * FROM products";

    List<Product> products = new ArrayList<>();

    try (Connection conn = JdbcConnection.getConnection();
        PreparedStatement allProducts = conn.prepareStatement(sql)) {

      ResultSet resultSet = allProducts.executeQuery();

      while (resultSet.next()) {
        Integer Id = resultSet.getInt("product_id");
        String productName = resultSet.getString("product_name");
        String size = resultSet.getString("product_size");
        String color = resultSet.getString("product_color");
        Integer quantity = resultSet.getInt("product_quantity");

        products.add(new Product(Id, productName, size, color, quantity));
      }

    } catch (SQLException e) {
      LOGGER.error("Failed to get all products" + e);
    }

    return products;
  }
}
