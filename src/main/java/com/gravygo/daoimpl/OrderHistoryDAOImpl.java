package com.gravygo.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.gravygo.dao.OrderHistoryDAO;
import com.gravygo.dbUtils.DBUtils;
import com.gravygo.model.OrderHistory;

public class OrderHistoryDAOImpl implements OrderHistoryDAO {

    Connection con;
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet res;
    List<OrderHistory> orderHistoryList = new ArrayList<>();
    OrderHistory orderHistory;

    private static final String ADD_ORDER_HISTORY = "INSERT INTO `order_history`(`orderId`, `userId`, `orderDate`, `totalAmount`, `status`) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_ORDER_HISTORIES = "SELECT * FROM `order_history`";
    private static final String SELECT_BY_ORDER_HISTORY_ID = "SELECT * FROM `order_history` WHERE `orderHistoryId` = ?";
    private static final String SELECT_BY_USER_ID = "SELECT * FROM `order_history` WHERE `userId` = ?";
    private static final String UPDATE_ORDER_HISTORY = "UPDATE `order_history` SET `orderId`=?, `userId`=?, `orderDate`=?, `totalAmount`=?, `status`=? WHERE `orderHistoryId`=?";
    private static final String DELETE_ORDER_HISTORY = "DELETE FROM `order_history` WHERE `orderHistoryId` = ?";

    public OrderHistoryDAOImpl() {
        try {
            con = DBUtils.myConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addOrderHistory(OrderHistory orderHistory) {
        try {
            pstmt = con.prepareStatement(ADD_ORDER_HISTORY);
            pstmt.setInt(1, orderHistory.getOrderId());
            pstmt.setInt(2, orderHistory.getUserId());
            pstmt.setString(3, orderHistory.getOrderDate());
            pstmt.setFloat(4, orderHistory.getTotalAmount());
            pstmt.setString(5, orderHistory.getStatus());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<OrderHistory> getAllOrderHistories() {
        try {
            stmt = con.createStatement();
            res = stmt.executeQuery(SELECT_ALL_ORDER_HISTORIES);
            extractOrderHistoriesFromResultSet(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }

    @Override
    public OrderHistory getOrderHistoryById(int orderHistoryId) {
        try {
            pstmt = con.prepareStatement(SELECT_BY_ORDER_HISTORY_ID);
            pstmt.setInt(1, orderHistoryId);
            res = pstmt.executeQuery();
            extractOrderHistoriesFromResultSet(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistoryList.isEmpty() ? null : orderHistoryList.get(0);
    }

    @Override
    public List<OrderHistory> getOrderHistoriesByUserId(int userId) {
        try {
            pstmt = con.prepareStatement(SELECT_BY_USER_ID);
            pstmt.setInt(1, userId);
            res = pstmt.executeQuery();
            extractOrderHistoriesFromResultSet(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }

    @Override
    public int updateOrderHistory(OrderHistory orderHistory) {
        try {
            pstmt = con.prepareStatement(UPDATE_ORDER_HISTORY);
            pstmt.setInt(1, orderHistory.getOrderId());
            pstmt.setInt(2, orderHistory.getUserId());
            pstmt.setString(3, orderHistory.getOrderDate());
            pstmt.setFloat(4, orderHistory.getTotalAmount());
            pstmt.setString(5, orderHistory.getStatus());
            pstmt.setInt(6, orderHistory.getOrderHistoryId());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteOrderHistory(int orderHistoryId) {
        try {
            pstmt = con.prepareStatement(DELETE_ORDER_HISTORY);
            pstmt.setInt(1, orderHistoryId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void extractOrderHistoriesFromResultSet(ResultSet res) throws SQLException {
        while (res.next()) {
            orderHistoryList.add(new OrderHistory(
                    res.getInt("orderHistoryId"),
                    res.getInt("orderId"),
                    res.getInt("userId"),
                    res.getString("orderDate"),
                    res.getFloat("totalAmount"),
                    res.getString("status")
            ));
        }
    }
}
