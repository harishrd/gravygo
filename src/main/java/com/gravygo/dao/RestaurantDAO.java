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
//
//
//package com.gravygo.dao;
//
//import java.util.List;
//import com.gravygo.model.Restaurant;
//
//public interface RestaurantDAO {
//    /**
//     * Adds a new restaurant to the database.
//     *
//     * @param restaurant The Restaurant object containing restaurant details, including imgPath.
//     * @return The number of rows affected in the database.
//     */
//    int addRestaurant(Restaurant restaurant);
//
//    /**
//     * Retrieves all restaurants from the database.
//     *
//     * @return A list of all Restaurant objects, including imgPath for each.
//     */
//    List<Restaurant> getAllRestaurants();
//
//    /**
//     * Retrieves a restaurant by its unique ID.
//     *
//     * @param restaurantId The ID of the restaurant to retrieve.
//     * @return A Restaurant object with the specified ID, including its imgPath.
//     */
//    Restaurant getRestaurantById(int restaurantId);
//
//    /**
//     * Updates an existing restaurant in the database.
//     *
//     * @param restaurant The Restaurant object containing updated details, including imgPath.
//     * @return The number of rows affected in the database.
//     */
//    int updateRestaurant(Restaurant restaurant);
//
//    /**
//     * Deletes a restaurant by its unique ID.
//     *
//     * @param restaurantId The ID of the restaurant to delete.
//     * @return The number of rows affected in the database.
//     */
//    int deleteRestaurant(int restaurantId);
//}