package com.Anderson.DBLMS.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {
	
	public Connection getConnection()
	{
		Connection conn = null;
		
		
		
		try {
			conn = ((Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezpme=UTC", "root", "root")) ;
		} catch(SQLException e)
		{
			System.out.println("Could not connect to the DB");
		}
		return conn;
	}

}
