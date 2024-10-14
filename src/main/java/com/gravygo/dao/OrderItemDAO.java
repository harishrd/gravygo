package com.gravygo.dao;

import java.util.List;
import com.gravygo.model.OrderItem;

public interface OrderItemDAO 
{
    int addOrderItem(OrderItem orderItem);
    List<OrderItem> getAllOrderItems();
    OrderItem getOrderItemById(int orderItemId);
    int updateOrderItem(OrderItem orderItem);
    int deleteOrderItem(int orderItemId);
}
