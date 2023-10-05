package com.practiceUni.shoppingWeb.dao.impl;

import com.practiceUni.shoppingWeb.JdbcConnection;
import com.practiceUni.shoppingWeb.dao.PurchaseDAO;
import com.practiceUni.shoppingWeb.domain.Purchase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PurchaseDAOImpl implements PurchaseDAO {

  private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseDAOImpl.class);

  @Override
  public Purchase create(Purchase purchase) {
    String sql = "INSERT INTO purchase(purchase_date,product_id, brand_id) VALUES (?,?,?)";

    try (Connection conn = JdbcConnection.getConnection();
         PreparedStatement createPurchase =
                 conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      createPurchase.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
      createPurchase.setInt(2, purchase.getProductId());
      createPurchase.setInt(3, purchase.getBrandId());

      int insertedRows = createPurchase.executeUpdate();

      if (insertedRows > 0) {
        ResultSet generatedKeys = createPurchase.getGeneratedKeys();

        if (generatedKeys.next()) {
          int generatedId = generatedKeys.getInt(1);
          purchase.setPurchaseId(generatedId);

          String userSql = "INSERT INTO user_purchase(user_id,purchase_id) VALUES (?,?)";
          try (PreparedStatement connectUserToPurchase = conn.prepareStatement(userSql)) {

            connectUserToPurchase.setInt(1, purchase.getUserId());
            connectUserToPurchase.setInt(2, generatedId);
            connectUserToPurchase.executeUpdate();
          }
        }
      }

    } catch (SQLException e) {
      LOGGER.error("Failed to create a purchase " + e);
    }

    return purchase;
  }


  @Override
  public boolean deleteById(Integer id) {
    String sql = "DELETE FROM purchase WHERE purchase_id = ?";
    String userPurchaseSql = "DELETE FROM user_purchase WHERE user_purchase.purchase_id + ?";

    try (Connection conn = JdbcConnection.getConnection();
        PreparedStatement deletePurchase = conn.prepareStatement(sql);
        PreparedStatement deleteUserPurchase = conn.prepareStatement(userPurchaseSql)) {

      deleteUserPurchase.setInt(1, id);
      deletePurchase.setInt(1, id);

      deleteUserPurchase.executeUpdate();
      deletePurchase.executeUpdate();

    } catch (SQLException e) {
      LOGGER.error("Failed to cancel the purchase with id: " + id + e);
    }

    return true;
  }

  @Override
  public Purchase findById(Integer id) {
    String sql =
        "SELECT purchase.purchase_id,"
            + " purchase.purchase_date,"
            + " user.user_id,"
            + " user.user_address,"
            + " purchase.product_id,"
            + "purchase.brand_id"
            + " FROM purchase "
            + "JOIN user_purchase"
            + " ON user_purchase.purchase_id = purchase.purchase_id "
            + "JOIN user "
            + "ON user_purchase.user_id = user.user_id "
            + " WHERE purchase.purchase_id = ?";
    Purchase purchase = new Purchase();

    try (Connection conn = JdbcConnection.getConnection();
        PreparedStatement findPurchase = conn.prepareStatement(sql)) {

      if (id != null) {
        findPurchase.setInt(1, id);
      } else {
        return null;
      }

      ResultSet resultSet = findPurchase.executeQuery();

      while (resultSet.next()) {
        Integer purchaseId = resultSet.getInt("purchase_id");
        Timestamp purchaseDateTime = resultSet.getTimestamp("purchase_date");
        Integer userId = resultSet.getInt("user_id");
        String userAddress = resultSet.getString("user_address");
        Integer productId = resultSet.getInt("product_id");
        Integer brandId = resultSet.getInt("brand_id");

        purchase =
            new Purchase(purchaseId, purchaseDateTime, userId, userAddress, productId, brandId);
      }

    } catch (SQLException e) {
      LOGGER.error("Failed to find a  purchase with id: " + id + e);
    }

    return purchase;
  }

  @Override
  public List<Purchase> findAllById(Integer id) {
    String sql =
        "SELECT purchase.purchase_id,"
            + " purchase.purchase_date,"
            + " user.user_id,"
            + " user.user_address,"
            + " purchase.product_id,"
            + "purchase.brand_id"
            + " FROM purchase "
            + "JOIN user_purchase"
            + " ON user_purchase.purchase_id = purchase.purchase_id "
            + "JOIN user "
            + "ON user_purchase.user_id = user.user_id "
            + " WHERE user.user_id = ?";
    List<Purchase> purchases = new ArrayList<>();

    try (Connection conn = JdbcConnection.getConnection();
        PreparedStatement getAllPurchase = conn.prepareStatement(sql)) {

      getAllPurchase.setInt(1, id);

      ResultSet resultSet = getAllPurchase.executeQuery();

      while (resultSet.next()) {
        Integer purchaseId = resultSet.getInt("purchase_id");
        Timestamp purchaseDateTime = resultSet.getTimestamp("purchase_date");
        Integer userId = resultSet.getInt("user_id");
        String userAddress = resultSet.getString("user_address");
        Integer productId = resultSet.getInt("product_id");
        Integer brandId = resultSet.getInt("brand_id");

        purchases.add(
            new Purchase(purchaseId, purchaseDateTime, userId, userAddress, productId, brandId));
      }

    } catch (SQLException e) {
      LOGGER.error("Failed to get all the purchases from user with id: " + id + e);
    }

    return purchases;
  }
}
