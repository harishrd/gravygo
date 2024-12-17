package com.gravygo.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gravygo.dao.MenuDAO;
import com.gravygo.dbUtils.DBUtils;
import com.gravygo.model.Menu;

public class MenuDAOImpl implements MenuDAO
{
    Connection con;
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet res;
    List<Menu> menuList;

    private static final String ADD_MENU = "insert into `menu`(`restaurantId`, `menuName`, `price`, `description`, `isAvailable`, `imgPath`) values(?,?,?,?,?,?)";
    private static final String SELECT_ALL_MENUS = "select * from `menu`";
    private static final String SELECT_ON_ID = "select * from `menu` where `menuId`=?";
    private static final String query = "SELECT * FROM menu WHERE restaurantId = ?";
    private static final String UPDATE_ON_ID = "update `menu` set `restaurantId`=?, `menuName`=?, `price`=?, `description`=?, `isAvailable`=?, `imgPath`=? where `menuId`=?";
    private static final String DELETE_ON_ID = "delete from `menu` where `menuId`=?";

    public MenuDAOImpl() 
    {
        try {
            con = DBUtils.myConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addMenu(Menu menu) {
        try {
            pstmt = con.prepareStatement(ADD_MENU);
            pstmt.setInt(1, menu.getRestaurantId());
            pstmt.setString(2, menu.getMenuName());
            pstmt.setFloat(3, menu.getPrice());
            pstmt.setString(4, menu.getDescription());
            pstmt.setBoolean(5, menu.isAvailable());
            pstmt.setString(6, menu.getImgPath());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Menu> getAllMenus() {
        try {
            stmt = con.createStatement();
            res = stmt.executeQuery(SELECT_ALL_MENUS);
            return extractMenusFromResultSet(res); // Use extractMenusFromResultSet to return the list
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Menu getMenuById(int menuId) {
        try {
            pstmt = con.prepareStatement(SELECT_ON_ID);
            pstmt.setInt(1, menuId);
            res = pstmt.executeQuery();
            return extractMenuFromResultSet(res); // Use extractMenuFromResultSet to return a single menu
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Menu> getMenusByRestaurantId(int restaurantId) {
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, restaurantId);
            try (ResultSet rs = ps.executeQuery()) {
                return extractMenusFromResultSet(rs); // Use extractMenusFromResultSet for the list of menus
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    
    @Override
    public int updateMenu(Menu menu) {
        try {
            pstmt = con.prepareStatement(UPDATE_ON_ID);
            pstmt.setInt(1, menu.getRestaurantId());
            pstmt.setString(2, menu.getMenuName());
            pstmt.setFloat(3, menu.getPrice());
            pstmt.setString(4, menu.getDescription());
            pstmt.setBoolean(5, menu.isAvailable());
            pstmt.setString(6, menu.getImgPath());
            pstmt.setInt(7, menu.getMenuId());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteMenu(int menuId) {
        try {
            pstmt = con.prepareStatement(DELETE_ON_ID);
            pstmt.setInt(1, menuId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private List<Menu> extractMenusFromResultSet(ResultSet res) {
        List<Menu> menuList = new ArrayList<>();
        try {
            while (res.next()) {
                menuList.add(createMenuFromResultSet(res));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }

    private Menu extractMenuFromResultSet(ResultSet res) {
        try {
            if (res.next()) {
                return createMenuFromResultSet(res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Menu createMenuFromResultSet(ResultSet res) throws SQLException {
        return new Menu(
            res.getInt("menuId"),
            res.getInt("restaurantId"),
            res.getString("menuName"),
            res.getFloat("price"),
            res.getString("description"),
            res.getBoolean("isAvailable"),
            res.getString("imgPath")
        );
    }
}