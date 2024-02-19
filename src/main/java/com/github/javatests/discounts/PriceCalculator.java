package com.github.javatests.discounts;

import java.util.List;
import java.util.ArrayList;

public class PriceCalculator {

  private List<Double> prices = new ArrayList<>();
  private double discount;

  public double getTotal() {
    double result = 0;

    for (Double price : prices) {
      result += price;
    }

    return result * ((100 - discount) / 100);
  }

  public void addPrice(double price) {
    prices.add(price);

  }

  public void setDiscount(double discount) {
    this.discount = discount;
  }
}
