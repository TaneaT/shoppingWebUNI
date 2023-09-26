package com.practiceUni.shoppingWeb.domain;

import java.util.Objects;

public class User {

  private Integer id;

  private String firstName;

  private String lastName;

  private String login;

  private String password;

  private String email;

  private String address;

  public User(
      Integer id,
      String firstName,
      String lastName,
      String login,
      String password,
      String email,
      String address) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.login = login;
    this.password = password;
    this.email = email;
    this.address = address;
  }

  public User(
      String firstName,
      String lastName,
      String login,
      String password,
      String email,
      String address) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.login = login;
    this.password = password;
    this.email = email;
    this.address = address;
  }

  public User(Integer id, String firstName, String lastName, String login, String email, String address) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.login = login;
    this.email = email;
    this.address = address;
  }

  public User() {}

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(id, user.id)
        && Objects.equals(firstName, user.firstName)
        && Objects.equals(lastName, user.lastName)
        && Objects.equals(login, user.login)
        && Objects.equals(password, user.password)
        && Objects.equals(email, user.email)
        && Objects.equals(address, user.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, login, password, email, address);
  }
}
