package com.explore.architecture.sample.orders.domain;

import com.explore.architecture.sample.orders.infra.OrderTable;

/** Sample domain class that breaks the layer rules by depending on infra. */
public class Order {

  private final OrderTable table = new OrderTable();

  /** Returns the infra type the rule should report. */
  public OrderTable table() {
    return table;
  }
}
