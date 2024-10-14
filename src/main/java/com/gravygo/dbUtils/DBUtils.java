package com.gravygo.dbUtils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils 
{
	public static Connection myConnect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gravygo", "root", "FLYINGFISH");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
}
