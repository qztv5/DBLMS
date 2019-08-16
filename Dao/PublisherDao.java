package com.Anderson.DBLMS.Dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Anderson.DBLMS.Entity.Publisher;

public class PublisherDao extends Dao {
	ResultSet publisher = null;
	PreparedStatement prepareStatement = null;
	Connection conn = getConnection();
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public ResultSet getAll() 
	{
		
		String sql = "Select * From library.tbl_publisher";
		try {
			prepareStatement = conn.prepareStatement(sql);
			publisher = prepareStatement.executeQuery();
		} catch (SQLException e) {
			System.out.println("Could not retrieve publishers");
		}
		return publisher;
	}
	
	public void add(Publisher p) throws SQLException
	{
		String sql = "insert into library.tbl_publisher (publisherID, publisherName, publisherAddress, publisherPhone) values (?,?,?,?)";
		prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(1, p.getPublisherId());
		prepareStatement.setString(2, p.getPublisherName());
		prepareStatement.setString(3, p.getPublisherAddress());
		prepareStatement.setString(4, p.getPublisherPhone());
		prepareStatement.execute();
	}
	
	public void remove(Publisher p) throws SQLException
	{
		String sql = "Delete From library.tbl_publisher where publisherID=?";
		prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(1, p.getPublisherId());
		prepareStatement.execute();
	}
	
	public void update(Publisher p) throws SQLException
	{
		String sql = "Update library.tbl_publisher set publisherName =?, publisherAddress=?, publisherPhone=? where publisherId=?";
		prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(4, p.getPublisherId());
		prepareStatement.setString(1, p.getPublisherName());
		prepareStatement.setString(2, p.getPublisherAddress());
		prepareStatement.setString(3, p.getPublisherPhone());
		prepareStatement.execute();
	}

}
