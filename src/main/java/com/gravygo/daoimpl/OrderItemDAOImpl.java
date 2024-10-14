package com.gravygo.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gravygo.dao.OrderItemDAO;
import com.gravygo.dbUtils.DBUtils;
import com.gravygo.model.OrderItem;

public class OrderItemDAOImpl implements OrderItemDAO
{
    Connection con;
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet res;
    List<OrderItem> orderItemList = new ArrayList<>();
    OrderItem orderItem;

    private static final String ADD_ORDER_ITEM = "insert into `order_item`(`orderId`, `menuId`, `quantity`, `subTotal`) values(?,?,?,?)";
    private static final String SELECT_ALL_ORDER_ITEMS = "select * from `order_item`";
    private static final String SELECT_ON_ID = "select * from `order_item` where `orderItemId`=?";
    private static final String UPDATE_ON_ID = "update `order_item` set `orderId`=?, `menuId`=?, `quantity`=?, `subTotal`=? where `orderItemId`=?";
    private static final String DELETE_ON_ID = "delete from `order_item` where `orderItemId`=?";

    public OrderItemDAOImpl() 
    {
        try {
            con = DBUtils.myConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addOrderItem(OrderItem orderItem) {
        try {
            pstmt = con.prepareStatement(ADD_ORDER_ITEM);
            pstmt.setInt(1, orderItem.getOrderId());
            pstmt.setInt(2, orderItem.getMenuId());
            pstmt.setInt(3, orderItem.getQuantity());
            pstmt.setFloat(4, orderItem.getSubTotal());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        try {
            stmt = con.createStatement();
            res = stmt.executeQuery(SELECT_ALL_ORDER_ITEMS);

            extractOrderItemsFromResultSet(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemList;
    }

    @Override
    public OrderItem getOrderItemById(int orderItemId) {
        try {
            pstmt = con.prepareStatement(SELECT_ON_ID);
            pstmt.setInt(1, orderItemId);
            res = pstmt.executeQuery();
            extractOrderItemsFromResultSet(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemList.get(0);
    }

    @Override
    public int updateOrderItem(OrderItem orderItem) {
        try {
            pstmt = con.prepareStatement(UPDATE_ON_ID);
            pstmt.setInt(1, orderItem.getOrderId());
            pstmt.setInt(2, orderItem.getMenuId());
            pstmt.setInt(3, orderItem.getQuantity());
            pstmt.setFloat(4, orderItem.getSubTotal());
            pstmt.setInt(5, orderItem.getOrderItemId());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteOrderItem(int orderItemId) {
        try {
            pstmt = con.prepareStatement(DELETE_ON_ID);
            pstmt.setInt(1, orderItemId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void extractOrderItemsFromResultSet(ResultSet res) {
        try {
            while (res.next()) {
                orderItemList.add(new OrderItem(
                    res.getInt("orderItemId"),
                    res.getInt("orderId"),
                    res.getInt("menuId"),
                    res.getInt("quantity"),
                    res.getFloat("subTotal")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
