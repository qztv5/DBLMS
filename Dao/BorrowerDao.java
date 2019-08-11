package com.Anderson.DBLMS.Dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Anderson.DBLMS.Entity.Borrower;

public class BorrowerDao extends Dao {
	Connection conn = getConnection();
	ResultSet borrower = null;
	PreparedStatement prepareStatement = null;
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public ResultSet getAll() 
	{
		
		String sql = "Select * From library.tbl_borrower";
		try {
			prepareStatement = conn.prepareStatement(sql);
			borrower = prepareStatement.executeQuery();
		} catch (SQLException e) {
			System.out.println("Could not retrieve borrowers");
		}
		return borrower;
	}
	
	public void add(Borrower b) throws SQLException
	{
		String sql = "insert into library.tbl_borrower (CardNo, name, address, phone) values (?,?,?,?)";
		prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(1, b.getCardNo());
		prepareStatement.setString(2, b.getName());
		prepareStatement.setString(3, b.getAddress());
		prepareStatement.setString(4, b.getPhone());
		prepareStatement.execute();
	}
	
	public void remove(Borrower b) throws SQLException
	{
		String sql = "Delete From library.tbl_borrower where CardNo=?";
		prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(1, b.getCardNo());
		prepareStatement.execute();
	}
	
	public void update(Borrower b) throws SQLException
	{
		String sql = "Update library.tbl_borrower set name =?, address =?, phone =? where CardNo =?";
		prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(4, b.getCardNo());
		prepareStatement.setString(1, b.getName());
		prepareStatement.setString(2, b.getAddress());
		prepareStatement.setString(3, b.getPhone());
		prepareStatement.execute();
	}
	

}