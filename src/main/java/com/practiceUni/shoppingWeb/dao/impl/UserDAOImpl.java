package com.practiceUni.shoppingWeb.dao.impl;

import com.practiceUni.shoppingWeb.JdbcConnection;
import com.practiceUni.shoppingWeb.dao.UserDAO;
import com.practiceUni.shoppingWeb.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class UserDAOImpl implements UserDAO {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserDAOImpl.class);

  @Override
  public User create(User user) {
    String sql =
        "INSERT INTO user(user_first_name, user_last_name, user_login, user_password, user_email, user_address) VALUES (?,?,?,?,?,?)";

    try (Connection conn = JdbcConnection.getConnection();
        PreparedStatement createUser =
            conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

      createUser.setString(1, user.getFirstName());
      createUser.setString(2, user.getLastName());
      createUser.setString(3, user.getLogin());
      createUser.setString(4, user.getPassword());
      createUser.setString(5, user.getEmail());
      createUser.setString(6, user.getAddress());

      int insertedRows = createUser.executeUpdate();

      if (insertedRows > 0) {
        ResultSet generatedKeys = createUser.getGeneratedKeys();

        if (generatedKeys.next()) {
          int generatedId = generatedKeys.getInt(1);
          user.setId(generatedId);
        }
      }

    } catch (SQLException e) {
      LOGGER.error("Failed to create the user" + e);
    }
    return user;
  }

  @Override
  public User update(User user) {
    String sql = "UPDATE user SET user_address = ? WHERE user_id = ?";

    try (Connection conn = JdbcConnection.getConnection();
        PreparedStatement updateUser = conn.prepareStatement(sql)) {

      updateUser.setString(1, user.getAddress());
      if(user.getId() != null){
      updateUser.setInt(2, user.getId());
      } else {
        return null;
      }

      updateUser.executeUpdate();

    } catch (SQLException e) {
      LOGGER.error("Failed to update the user with id: " + user.getId() + e);
    }

    return user;
  }

  @Override
  public boolean deleteById(Integer id) {
    String sql = "DELETE FROM user WHERE user_id = ?";

    try (Connection conn = JdbcConnection.getConnection();
        PreparedStatement deleteUser = conn.prepareStatement(sql)) {

      deleteUser.setInt(1, id);

      deleteUser.executeUpdate();

    } catch (SQLException e) {
      LOGGER.error("Failed to delete the user with id: " + id + e);
    }

    return true;
  }

  @Override
  public User findById(Integer id) {
    String sql = "SELECT * FROM user WHERE user_id = ?";

    User user = new User();

    try (Connection conn = JdbcConnection.getConnection();
        PreparedStatement findUser = conn.prepareStatement(sql)) {

      if (id != null) {
        findUser.setInt(1, id);
      } else {
        return null;
      }

      ResultSet resultSet = findUser.executeQuery();

      while (resultSet.next()) {
        Integer userId = resultSet.getInt("user_id");
        String firstName = resultSet.getString("user_first_name");
        String lastName = resultSet.getString("user_last_name");
        String login = resultSet.getString("user_login");
        String password = resultSet.getString("user_password");
        String email = resultSet.getString("user_email");
        String address = resultSet.getString("user_address");

        user = new User(userId, firstName, lastName, login, password, email, address);
      }

    } catch (SQLException e) {
      LOGGER.error("Failed to find a user with id: " + id);
      return null;
    }

    return user;
  }

  @Override
  public User findByLogin(String login) {
    String sql = "SELECT * FROM user WHERE user_login = ?";

    User user = new User();

    try (Connection conn = JdbcConnection.getConnection();
        PreparedStatement findUser = conn.prepareStatement(sql)) {

      findUser.setString(1, login);

      ResultSet resultSet = findUser.executeQuery();

      while (resultSet.next()) {
        Integer id = resultSet.getInt("user_id");
        String firstName = resultSet.getString("user_first_name");
        String lastName = resultSet.getString("user_last_name");
        String userLogin = resultSet.getString("user_login");
        String password = resultSet.getString("user_password");
        String email = resultSet.getString("user_email");
        String address = resultSet.getString("user_address");

        user = new User(id, firstName, lastName, userLogin, password, email, address);
      }
    } catch (SQLException e) {
      LOGGER.error("Failed to find a user with login: " + login);
      return null;
    }

    return user;
  }
}
