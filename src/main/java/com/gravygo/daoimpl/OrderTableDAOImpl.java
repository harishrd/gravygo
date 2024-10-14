package com.gravygo.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gravygo.dao.OrderTableDAO;
import com.gravygo.dbUtils.DBUtils;
import com.gravygo.model.OrderTable;

public class OrderTableDAOImpl implements OrderTableDAO
{
    Connection con;
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet res;
    List<OrderTable> orderList = new ArrayList<>();
    OrderTable order;

    private static final String ADD_ORDER = "insert into `order_table`(`restaurantId`, `userId`, `orderDate`, `totalAmount`, `status`, `paymentMode`) values(?,?,?,?,?,?)";
    private static final String SELECT_ALL_ORDERS = "select * from `order_table`";
    private static final String SELECT_ON_ID = "select * from `order_table` where `orderId`=?";
    private static final String UPDATE_ON_ID = "update `order_table` set `restaurantId`=?, `userId`=?, `orderDate`=?, `totalAmount`=?, `status`=?, `paymentMode`=? where `orderId`=?";
    private static final String DELETE_ON_ID = "delete from `order_table` where `orderId`=?";

    public OrderTableDAOImpl() 
    {
        try {
            con = DBUtils.myConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addOrder(OrderTable order) {
        try {
            pstmt = con.prepareStatement(ADD_ORDER);
            pstmt.setInt(1, order.getRestaurantId());
            pstmt.setInt(2, order.getUserId());
            pstmt.setString(3, order.getOrderDate());
            pstmt.setFloat(4, order.getTotalAmount());
            pstmt.setString(5, order.getStatus());
            pstmt.setString(6, order.getPaymentMode());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<OrderTable> getAllOrders() {
        try {
            stmt = con.createStatement();
            res = stmt.executeQuery(SELECT_ALL_ORDERS);

            extractOrdersFromResultSet(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public OrderTable getOrderById(int orderId) {
        try {
            pstmt = con.prepareStatement(SELECT_ON_ID);
            pstmt.setInt(1, orderId);
            res = pstmt.executeQuery();
            extractOrdersFromResultSet(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList.get(0);
    }

    @Override
    public int updateOrder(OrderTable order) {
        try {
            pstmt = con.prepareStatement(UPDATE_ON_ID);
            pstmt.setInt(1, order.getRestaurantId());
            pstmt.setInt(2, order.getUserId());
            pstmt.setString(3, order.getOrderDate());
            pstmt.setFloat(4, order.getTotalAmount());
            pstmt.setString(5, order.getStatus());
            pstmt.setString(6, order.getPaymentMode());
            pstmt.setInt(7, order.getOrderId());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteOrder(int orderId) {
        try {
            pstmt = con.prepareStatement(DELETE_ON_ID);
            pstmt.setInt(1, orderId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void extractOrdersFromResultSet(ResultSet res) {
        try {
            while (res.next()) {
                orderList.add(new OrderTable(
                    res.getInt("orderId"),
                    res.getInt("restaurantId"),
                    res.getInt("userId"),
                    res.getString("orderDate"),
                    res.getFloat("totalAmount"),
                    res.getString("status"),
                    res.getString("paymentMode")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
