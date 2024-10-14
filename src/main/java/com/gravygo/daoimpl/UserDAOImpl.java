package com.gravygo.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gravygo.dao.UserDAO;
import com.gravygo.dbUtils.DBUtils;
import com.gravygo.model.User;


public class UserDAOImpl implements UserDAO
{
	Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet res;
	List<User> userList = new ArrayList<>();
	User user;

	private static final String ADD_USER = "insert into `user`(`username`,`email`,`phoneNumber`,`password`,`address`) values(?,?,?,?,?)";
	private static final String SELECT_ALL_USERS = "select * from `user`";
	private static final String SELECT_ON_EMAIL = "select * from `user` where email=?";
	private static final String UPDATE_ON_EMAIL = "update `user` set `username`=?, `phoneNumber`=?, `password`=?, `address`=? where `email`=?";
	private static final String DELETE_ON_EMAIL = "delete from `user` where `email`=?";

	public UserDAOImpl() // CONSTRUCTOR
	{
		try
		{
			con = DBUtils.myConnect();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public int addUser(User user) {
		try 
		{
			pstmt = con.prepareStatement(ADD_USER);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPhoneNumber());
			pstmt.setString(4, user.getPassword());
			pstmt.setString(5, user.getAddress());

			return pstmt.executeUpdate();
		} 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<User> getAllUsers() {
		try 
		{
			stmt = con.createStatement();
			res = stmt.executeQuery(SELECT_ALL_USERS);

			extractUsersFromResultSet(res);
		} 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public User getUser(String email) {
		try 
		{
			pstmt = con.prepareStatement(SELECT_ON_EMAIL);
			pstmt.setString(1, email);
			res = pstmt.executeQuery();
			extractUsersFromResultSet(res);
		} 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		return userList.get(0);
	}

	@Override
	public int updateUser(User u) {
		try 
		{
			pstmt = con.prepareStatement(UPDATE_ON_EMAIL);
			pstmt.setString(1, u.getUsername());
			pstmt.setString(2, u.getPhoneNumber());
			pstmt.setString(3, u.getPassword());
			pstmt.setString(4, u.getAddress());
			pstmt.setString(5, u.getEmail());
			return pstmt.executeUpdate();
		} 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteUser(String email) {
		try 
		{
			pstmt = con.prepareStatement(DELETE_ON_EMAIL);
			pstmt.setString(1, email);
			return pstmt.executeUpdate();
		} 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		return 0;
	}

	private void extractUsersFromResultSet(ResultSet res)
	{
		try {
			while (res.next()) 
			{
				userList.add(new User(res.getInt("userId"),
						res.getString("username"),
						res.getString("email"),
						res.getString("phoneNumber"),
						res.getString("password"),
						res.getString("address")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
