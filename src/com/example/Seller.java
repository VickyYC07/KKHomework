package com.example;

import java.util.Objects;

public class Seller {

  private final String id;

  private final long serialNumber;

  private final String nickname;

  public Seller(String id, long serialNumber, String nickname) {
    this.id = id;
    this.serialNumber = serialNumber;
    this.nickname = nickname;
  }

  public String getId() {
    return id;
  }

  public long getSerialNumber() {
    return serialNumber;
  }

  public String getNickname() {
    return nickname;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    final Seller that = (Seller) o;
    return serialNumber == that.serialNumber && Objects.equals(id, that.id) && Objects.equals(
        nickname,
        that.nickname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, serialNumber, nickname);
  }

  @Override
  public String toString() {
    return "Seller{"
        + "id='"
        + id
        + '\''
        + ", serialNumber="
        + serialNumber
        + ", nickname='"
        + nickname
        + '\''
        + '}';
  }
}

