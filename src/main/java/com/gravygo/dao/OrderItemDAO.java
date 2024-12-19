package com.gravygo.dao;

import java.util.List;
import com.gravygo.model.OrderItem;

public interface OrderItemDAO 
{
    int addOrderItem(OrderItem orderItem);
    OrderItem getOrderItemById(int orderItemId);
    int updateOrderItem(OrderItem orderItem);
    int deleteOrderItem(int orderItemId);
	List<OrderItem> getItemsByOrderId(int orderId);
}
