package com.gravygo.dao;

import java.util.List;
import com.gravygo.model.Restaurant;

public interface RestaurantDAO 
{
    int addRestaurant(Restaurant restaurant);
    List<Restaurant> getAllRestaurants();
    Restaurant getRestaurantById(int restaurantId);
    int updateRestaurant(Restaurant restaurant);
    int deleteRestaurant(int restaurantId);
}
