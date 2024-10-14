package com.gravygo.dao;

import java.util.List;
import com.gravygo.model.OrderTable;

public interface OrderTableDAO 
{
    int addOrder(OrderTable order);
    List<OrderTable> getAllOrders();
    OrderTable getOrderById(int orderId);
    int updateOrder(OrderTable order);
    int deleteOrder(int orderId);
}
