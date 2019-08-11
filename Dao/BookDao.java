package com.Anderson.DBLMS.Dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Anderson.DBLMS.Entity.Book;

public class BookDao extends Dao {
	
	Connection conn = getConnection();
	ResultSet book = null;
	PreparedStatement prepareStatement = null;
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public ResultSet getAll() 
	{
		
		String sql = "Select * From library.tbl_book";
		try {
			prepareStatement = conn.prepareStatement(sql);
			book = prepareStatement.executeQuery();
		} catch (SQLException e) {
			System.out.println("Could not retrieve books");
		}
		return book;
	}
	
	public void add(Book b) throws SQLException
	{
		String sql = "insert into library.tbl_book (bookID, title, authId, pubId) values (?,?,?,?)";
		prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(1, b.getBookId());
		prepareStatement.setString(2, b.getTitle());
		prepareStatement.setInt(3, b.getAuthId());
		prepareStatement.setInt(4, b.getPubId());
		prepareStatement.execute();
	}
	
	public void remove(Book b) throws SQLException
	{
		String sql = "Delete From library.tbl_book where bookID=?";
		prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(1, b.getBookId());
		prepareStatement.execute();
	}
	
	public void update(Book b) throws SQLException
	{
		String sql = "Update library.tbl_book set title =? where bookId =?";
		prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(2, b.getBookId());
		prepareStatement.setString(1, b.getTitle());
		prepareStatement.execute();
	}

}
