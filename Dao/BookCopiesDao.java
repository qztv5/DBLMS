package com.Anderson.DBLMS.Dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Anderson.DBLMS.Entity.BookCopy;

public class BookCopiesDao extends Dao{
	
	Connection conn = getConnection();
	ResultSet bookLoans = null;
	PreparedStatement prepareStatement = null;
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public ResultSet getAll() 
	{
		
		String sql = "Select * From library.tbl_book_copies";
		try {
			prepareStatement = conn.prepareStatement(sql);
			bookLoans = prepareStatement.executeQuery();
		} catch (SQLException e) {
			System.out.println("Could not retrieve book loans");
		}
		return bookLoans;
	}
	
	public void add(BookCopy b) throws SQLException
	{
		String sql = "insert into library.tbl_book_copies (bookID, branchId, noOfCopies) values (?,?,?)";
		prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(1, b.getBookId());
		prepareStatement.setInt(2, b.getBranchId());
		prepareStatement.setInt(3, b.getNoOfCopies());
		prepareStatement.execute();
	}
	
	public void remove(BookCopy b) throws SQLException
	{
		String sql = "Delete From library.tbl_book_copies where bookID=? and branchId=?";
		prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(1, b.getBookId());
		prepareStatement.setInt(2, b.getBranchId());
		prepareStatement.setInt(3, b.getNoOfCopies());
		prepareStatement.execute();
	}
	
	public void update(BookCopy b) throws SQLException
	{
		String sql = "Update library.tbl_book_copies set noOfCopies =? where bookId =? and branchId=?";
		prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(2, b.getBookId());
		prepareStatement.setInt(3, b.getBranchId());
		prepareStatement.setInt(1, b.getNoOfCopies());
		prepareStatement.execute();
	}

}
