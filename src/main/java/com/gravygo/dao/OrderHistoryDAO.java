package com.gravygo.dao;

import java.util.List;
import com.gravygo.model.OrderHistory;

public interface OrderHistoryDAO {
    int addOrderHistory(OrderHistory orderHistory);
    List<OrderHistory> getAllOrderHistories();
    OrderHistory getOrderHistoryById(int orderHistoryId);
    List<OrderHistory> getOrderHistoriesByUserId(int userId);
    int updateOrderHistory(OrderHistory orderHistory);
    int deleteOrderHistory(int orderHistoryId);
}
