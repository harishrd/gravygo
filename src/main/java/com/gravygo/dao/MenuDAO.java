package com.gravygo.dao;

import java.util.List;
import com.gravygo.model.Menu;

public interface MenuDAO 
{
    int addMenu(Menu menu);
    List<Menu> getAllMenus();
    Menu getMenuById(int menuId);
    int updateMenu(Menu menu);
    int deleteMenu(int menuId);
}
