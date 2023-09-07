package com.practiceUni.shoppingWeb.domain;

import java.util.Objects;

public class Product {

  private Integer id;

  private String name;

  private String size;

  private String color;

  private Integer quantity;

  public Product(Integer id, String name, String size, String color, Integer quantity) {
    this.id = id;
    this.name = name;
    this.size = size;
    this.color = color;
    this.quantity = quantity;
  }

  public Product(String name, String size, String color, Integer quantity) {
    this.name = name;
    this.size = size;
    this.color = color;
    this.quantity = quantity;
  }

  public Product() {}

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return Objects.equals(id, product.id)
        && Objects.equals(name, product.name)
        && Objects.equals(size, product.size)
        && Objects.equals(color, product.color)
        && Objects.equals(quantity, product.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, size, color, quantity);
  }
}
