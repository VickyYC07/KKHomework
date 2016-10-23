package com.example;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class Item  {

  private final String id;
  private final Seller seller;
  private final long serialNumber;
  private final String name;
  private final String description;
  private final BigDecimal price;
  private final Instant createTime;

  public Item(String id,
      Seller seller,
      long serialNumber,
      String name,
      String description,
      BigDecimal price,
      Instant createTime) {
    this.id = id;
    this.seller = seller;
    this.serialNumber = serialNumber;
    this.name = name;
    this.description = description;
    this.price = price;
    this.createTime = createTime;
  }

  public String getId() {
    return id;
  }

  public Seller getSeller() {
    return seller;
  }

  public long getSerialNumber() {
    return serialNumber;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public Instant getCreateTime() {
    return createTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    final Item that = (Item) o;
    return serialNumber == that.serialNumber
        && Objects.equals(id, that.id)
        && Objects.equals(seller, that.seller)
        && Objects.equals(name, that.name)
        && Objects.equals(description, that.description)
        && Objects.equals(price, that.price)
        && Objects.equals(createTime, that.createTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, seller, serialNumber, name, description, price, createTime);
  }

  @Override
  public String toString() {
    return "Item{"
        + "id='"
        + id
        + '\''
        + ", seller="
        + seller
        + ", serialNumber="
        + serialNumber
        + ", name='"
        + name
        + '\''
        + ", description='"
        + description
        + '\''
        + ", price="
        + price
        + ", createTime="
        + createTime
        + '}';
  }
}

