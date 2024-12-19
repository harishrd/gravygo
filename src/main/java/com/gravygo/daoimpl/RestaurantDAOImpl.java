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

public class RestaurantDAOImpl implements RestaurantDAO {
    Connection con;
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet res;
    List<Restaurant> restaurantList = new ArrayList<>();
    Restaurant restaurant;

    
    private static final String ADD_RESTAURANT = "INSERT INTO `restaurant`(`restaurantName`, `deliveryTime`, `cuisineType`, `address`, `rating`, `isActive`, `adminId`, `imgPath`) VALUES(?,?,?,?,?,?,?,?)";
    private static final String SELECT_ALL_RESTAURANTS = "SELECT * FROM `restaurant`";
    private static final String SELECT_ON_ID = "SELECT * FROM `restaurant` WHERE `restaurantId`=?";
    private static final String UPDATE_ON_ID = "UPDATE `restaurant` SET `restaurantName`=?, `deliveryTime`=?, `cuisineType`=?, `address`=?, `rating`=?, `isActive`=?, `adminId`=?, `imgPath`=? WHERE `restaurantId`=?";
    private static final String DELETE_ON_ID = "DELETE FROM `restaurant` WHERE `restaurantId`=?";
    private static final String GET_NAME_ON_ID = "SELECT `restaurantName` FROM `restaurant` WHERE `restaurantId`=?";
    
    
    public RestaurantDAOImpl() {
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
            pstmt.setString(8, restaurant.getImgPath());

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

            restaurantList.clear();
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

            if (res.next()) {
                return new Restaurant(
                    res.getInt("restaurantId"),
                    res.getString("restaurantName"),
                    res.getInt("deliveryTime"),
                    res.getString("cuisineType"),
                    res.getString("address"),
                    res.getDouble("rating"),
                    res.getBoolean("isActive"),
                    res.getInt("adminId"),
                    res.getString("imgPath")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
            pstmt.setString(8, restaurant.getImgPath());
            pstmt.setInt(9, restaurant.getRestaurantId());

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

    
    @Override
    public String getRestaurantNameById(int restaurantId)
    {
    	try {
    		pstmt = con.prepareStatement(GET_NAME_ON_ID);
    		pstmt.setInt(1, restaurantId);
    		res = pstmt.executeQuery();
    		if (res.next()) {
    			return res.getString("restaurantName");    			
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return "Restaurant name is not available!";
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
                    res.getInt("adminId"),
                    res.getString("imgPath")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}