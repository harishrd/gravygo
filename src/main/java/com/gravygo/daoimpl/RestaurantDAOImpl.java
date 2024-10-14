package com.gravygo.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gravygo.dao.RestaurantDAO;
import com.gravygo.dbUtils.DBUtils;
import com.gravygo.model.Restaurant;

public class RestaurantDAOImpl implements RestaurantDAO
{
    Connection con;
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet res;
    List<Restaurant> restaurantList = new ArrayList<>();
    Restaurant restaurant;

    private static final String ADD_RESTAURANT = "insert into `restaurant`(`restaurantName`, `deliveryTime`, `cuisineType`, `address`, `rating`, `isActive`, `adminId`) values(?,?,?,?,?,?,?)";
    private static final String SELECT_ALL_RESTAURANTS = "select * from `  `";
    private static final String SELECT_ON_ID = "select * from `restaurant` where `restaurantId`=?";
    private static final String UPDATE_ON_ID = "update `restaurant` set `restaurantName`=?, `deliveryTime`=?, `cuisineType`=?, `address`=?, `rating`=?, `isActive`=?, `adminId`=? where `restaurantId`=?";
    private static final String DELETE_ON_ID = "delete from `restaurant` where `restaurantId`=?";

    public RestaurantDAOImpl() 
    {
        try {
            con = DBUtils.myConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addRestaurant(Restaurant restaurant) {
        try {
            pstmt = con.prepareStatement(ADD_RESTAURANT);
            pstmt.setString(1, restaurant.getRestaurantName());
            pstmt.setInt(2, restaurant.getDeliveryTime());
            pstmt.setString(3, restaurant.getCuisineType());
            pstmt.setString(4, restaurant.getAddress());
            pstmt.setDouble(5, restaurant.getRating());
            pstmt.setBoolean(6, restaurant.isActive());
            pstmt.setInt(7, restaurant.getAdminId());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        try {
            stmt = con.createStatement();
            res = stmt.executeQuery(SELECT_ALL_RESTAURANTS);

            extractRestaurantsFromResultSet(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurantList;
    }

    @Override
    public Restaurant getRestaurantById(int restaurantId) {
        try {
            pstmt = con.prepareStatement(SELECT_ON_ID);
            pstmt.setInt(1, restaurantId);
            res = pstmt.executeQuery();
            extractRestaurantsFromResultSet(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurantList.get(0);
    }

    @Override
    public int updateRestaurant(Restaurant restaurant) {
        try {
            pstmt = con.prepareStatement(UPDATE_ON_ID);
            pstmt.setString(1, restaurant.getRestaurantName());
            pstmt.setInt(2, restaurant.getDeliveryTime());
            pstmt.setString(3, restaurant.getCuisineType());
            pstmt.setString(4, restaurant.getAddress());
            pstmt.setDouble(5, restaurant.getRating());
            pstmt.setBoolean(6, restaurant.isActive());
            pstmt.setInt(7, restaurant.getAdminId());
            pstmt.setInt(8, restaurant.getRestaurantId());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteRestaurant(int restaurantId) {
        try {
            pstmt = con.prepareStatement(DELETE_ON_ID);
            pstmt.setInt(1, restaurantId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void extractRestaurantsFromResultSet(ResultSet res) {
        try {
            while (res.next()) {
                restaurantList.add(new Restaurant(
                    res.getInt("restaurantId"),
                    res.getString("restaurantName"),
                    res.getInt("deliveryTime"),
                    res.getString("cuisineType"),
                    res.getString("address"),
                    res.getDouble("rating"),
                    res.getBoolean("isActive"),
                    res.getInt("adminId")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
