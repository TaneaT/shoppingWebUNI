package com.practiceUni.shoppingWeb.dao.impl;

import com.practiceUni.shoppingWeb.JdbcConnection;
import com.practiceUni.shoppingWeb.dao.BrandDAO;
import com.practiceUni.shoppingWeb.domain.Brand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BrandDAOImpl implements BrandDAO {

  private static final Logger LOGGER = LoggerFactory.getLogger(BrandDAOImpl.class);

  @Override
  public Brand create(Brand brand) {
    String sql = "INSERT INTO brands(brand_name, brand_email) VALUES (?,?)";

    try (Connection conn = JdbcConnection.getConnection();
        PreparedStatement createBrand =
            conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

      createBrand.setString(1, brand.getName());
      createBrand.setString(2, brand.getEmail());

      int insertedRows = createBrand.executeUpdate();

      if (insertedRows > 0) {
        ResultSet generatedKeys = createBrand.getGeneratedKeys();

        if (generatedKeys.next()) {
          int generatedId = generatedKeys.getInt(1);
          brand.setId(generatedId);

          String connectsProductIdToBrandsSQL =
              "INSERT INTO brands_product(product_id, brand_id) VALUES (?,?)";

          try (PreparedStatement productsIdToBrand =
              conn.prepareStatement(connectsProductIdToBrandsSQL)) {
            if (brand.getId() != null) {
              productsIdToBrand.setInt(1, brand.getProductId());
            }
            productsIdToBrand.setInt(2, generatedId);
            productsIdToBrand.executeUpdate();
          }
        }
      }

    } catch (SQLException e) {
      LOGGER.error("Failed to create a brand " + e);
      e.printStackTrace();
    }

    return brand;
  }

  @Override
  public Brand update(Brand brand) {
    String sql = "Update brands SET brand_email = ? WHERE brand_id = ?";

    try (Connection conn = JdbcConnection.getConnection();
        PreparedStatement updateBrand = conn.prepareStatement(sql)) {

      updateBrand.setString(1, brand.getEmail());
      updateBrand.setInt(2, brand.getId());

      updateBrand.executeUpdate();

    } catch (SQLException e) {
      LOGGER.error("Failed to update the brand with id : " + brand.getId() + e);
    }

    return brand;
  }

  @Override
  public boolean deleteById(Integer id) {
    String sql = "DELETE FROM brands WHERE brand_id = ?";

    try (Connection conn = JdbcConnection.getConnection();
        PreparedStatement deleteBrand = conn.prepareStatement(sql)) {

      deleteBrand.setInt(1, id);
      deleteBrand.executeUpdate();

    } catch (SQLException e) {
      LOGGER.error("Failed to delete the brand with id: " + id + e);
    }

    return true;
  }

  @Override
  public Brand findById(Integer id) {
    String sql =
        "SELECT brands.brand_id,"
            + "brands.brand_name,"
            + "brands.brand_email,"
            + "products.product_id ,"
            + "products.product_name ,"
           + " products.product_category,"
            + "products.product_size ,"
            + "products.product_color "
            + " FROM brands "
            + "JOIN brands_product "
            + "ON brands_product.brand_id = brands.brand_id "
            + "JOIN products ON brands_product.product_id = products.product_id "
            + "WHERE brands.brand_id = ?";

    Brand brand = new Brand();

    try (Connection conn = JdbcConnection.getConnection();
        PreparedStatement findBrand = conn.prepareStatement(sql)) {

      if (id != null) {
        findBrand.setInt(1, id);
      } else {
        return null;
      }

      ResultSet resultSet = findBrand.executeQuery();

      while (resultSet.next()) {
        Integer brandId = resultSet.getInt("brand_id");
        String name = resultSet.getString("brand_name");
        String email = resultSet.getString("brand_email");
        Integer productId = resultSet.getInt("product_id");
        String productName = resultSet.getString("product_name");
        String productCategory = resultSet.getString("product_category");
        String productSize = resultSet.getString("product_size");
        String productColor = resultSet.getString("product_color");

        brand = new Brand(brandId, name, email, productId, productName,productCategory, productSize, productColor);
      }

    } catch (SQLException e) {
      LOGGER.error("Failed to find a brand with id: " + id + e);
    }

    return brand;
  }

  @Override
  public Brand findByName(String name) {
    String sql =
        "SELECT brands.brand_id,"
            + "brands.brand_name,"
            + "brands.brand_email,"
            + "products.product_id ,"
            + "products.product_name,"
           + " products.product_category,"
            + "products.product_size,"
            + "products.product_color"
            + " FROM brands "
            + "JOIN brands_product "
            + "ON brands_product.brand_id = brands.brand_id "
            + "JOIN products ON brands_product.product_id = products.product_id "
            + "WHERE brands.brand_name = ?";

    Brand brand = new Brand();

    try (Connection conn = JdbcConnection.getConnection();
        PreparedStatement findBrand = conn.prepareStatement(sql)) {

      findBrand.setString(1, name);

      ResultSet resultSet = findBrand.executeQuery();

      while (resultSet.next()) {
        Integer brandId = resultSet.getInt("brand_id");
        String brandName = resultSet.getString("brand_name");
        String email = resultSet.getString("brand_email");
        Integer productId = resultSet.getInt("product_id");
        String productName = resultSet.getString("product_name");
        String productCategory = resultSet.getString("product_category");
        String productSize = resultSet.getString("product_size");
        String productColor = resultSet.getString("product_color");

        brand =
            new Brand(brandId, brandName, email, productId, productName,productCategory, productSize, productColor);
      }

    } catch (SQLException e) {
      LOGGER.error("Failed to find a brand with the name: " + name + e);
    }

    return brand;
  }

  @Override
  public List<Brand> getAll() {
    String sql =
        "SELECT brands.brand_id,"
            + "brands.brand_name,"
            + "brands.brand_email,"
            + "products.product_id AS product_id,"
            + "products.product_name,"
            + " products.product_category,"
            + "products.product_size,"
            + "products.product_color"
            + " FROM brands "
            + "JOIN brands_product "
            + "ON brands_product.brand_id = brands.brand_id "
            + "JOIN products ON brands_product.product_id = products.product_id ";

    List<Brand> brands = new ArrayList<>();

    try (Connection conn = JdbcConnection.getConnection();
        PreparedStatement getAllBrands = conn.prepareStatement(sql)) {

      ResultSet resultSet = getAllBrands.executeQuery();

      while (resultSet.next()) {
        Integer brandId = resultSet.getInt("brand_id");
        String name = resultSet.getString("brand_name");
        String email = resultSet.getString("brand_email");
        Integer productId = resultSet.getInt("product_id");
        String productName = resultSet.getString("product_name");
        String productCategory = resultSet.getString("product_category");
        String productSize = resultSet.getString("product_size");
        String productColor = resultSet.getString("product_color");

        brands.add(
            new Brand(brandId, name, email, productId, productName,productCategory, productSize, productColor));
      }

    } catch (SQLException e) {
      LOGGER.error("Failed to get all brands" + e);
    }

    return brands;
  }
}
