package com.practiceUni.shoppingWeb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {

  private static final String url = "jdbc:mysql://localhost:3306/sweb";

  private static final String user = "root";

  private static final String password = "";

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(url, user, password);
  }
}
