package com.practiceUni.shoppingWeb.domain;

import java.util.Objects;

public class Brand {

  private Integer id;

  private String name;

  private String email;

  private Integer productId;

  private String productName;

  private String productCategory;

  private String productSize;

  private String productColor;

  public Brand(Integer id, String name, String email, Integer productId, String productName, String productCategory, String productSize, String productColor) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.productId = productId;
    this.productName = productName;
    this.productCategory = productCategory;
    this.productSize = productSize;
    this.productColor = productColor;
  }

  public Brand(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public Brand(String name, String email,Integer productId) {
    this.name = name;
    this.email = email;
    this.productId = productId;
  }

  public Brand(String name, String email, Integer productId, String productName, String productCategory, String productSize, String productColor) {
    this.name = name;
    this.email = email;
    this.productId = productId;
    this.productName = productName;
    this.productCategory = productCategory;
    this.productSize = productSize;
    this.productColor = productColor;
  }

  public Brand(Integer id, String name, String email, Integer productId) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.productId = productId;
  }

  public Brand() {}

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getProductId() {
    return productId;
  }

  public void setProductId(Integer productId) {
    this.productId = productId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getProductCategory() {
    return productCategory;
  }

  public void setProductCategory(String productCategory) {
    this.productCategory = productCategory;
  }

  public String getProductSize() {
    return productSize;
  }

  public void setProductSize(String productSize) {
    this.productSize = productSize;
  }

  public String getProductColor() {
    return productColor;
  }

  public void setProductColor(String productColor) {
    this.productColor = productColor;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Brand brand = (Brand) o;
    return Objects.equals(id, brand.id) && Objects.equals(productId, brand.productId) && Objects.equals(name, brand.name) && Objects.equals(email, brand.email) && Objects.equals(productName, brand.productName) && Objects.equals(productCategory, brand.productCategory) && Objects.equals(productSize, brand.productSize) && Objects.equals(productColor, brand.productColor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, productId, name, email, productName, productCategory, productSize, productColor);
  }
}