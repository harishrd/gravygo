package com.gravygo.dao;

import java.util.List;
import com.gravygo.model.Menu;

public interface MenuDAO {
    int addMenu(Menu menu);                       // Add a new menu
    List<Menu> getAllMenus();                    // Get all menus (system-wide)
    Menu getMenuById(int menuId);                // Get a specific menu by its ID
    int updateMenu(Menu menu);                   // Update a menu
    int deleteMenu(int menuId);                  // Delete a menu by its ID

    // New method to retrieve all menus for a specific restaurant
    List<Menu> getMenusByRestaurantId(int restaurantId);
}