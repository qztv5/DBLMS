package com.Anderson.DBLMS.Dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Anderson.DBLMS.Entity.Book;
import com.Anderson.DBLMS.Entity.BookLoans;

public class BookLoansDao extends Dao {

	Connection conn = getConnection();
	ResultSet bookLoans = null;
	PreparedStatement prepareStatement = null;
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public ResultSet getAll() 
	{
		
		String sql = "Select * From library.tbl_book_loans";
		try {
			prepareStatement = conn.prepareStatement(sql);
			bookLoans = prepareStatement.executeQuery();
		} catch (SQLException e) {
			System.out.println("Could not retrieve book loans");
		}
		return bookLoans;
	}
	
	public void add(BookLoans b) throws SQLException
	{
		String sql = "insert into library.tbl_book_loans (bookID, branchId, cardNo, dateOut, dueDate) values (?,?,?,?,?)";
		prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(1, b.getBookId());
		prepareStatement.setInt(2, b.getBranchId());
		prepareStatement.setInt(3, b.getCardNo());
		prepareStatement.setDate(4, (Date) b.getDateOut());
		prepareStatement.setDate(5, (Date) b.getDueDate());
		prepareStatement.execute();
	}
	
	public void remove(BookLoans b) throws SQLException
	{
		String sql = "Delete From library.tbl_book_loans where bookID=? and branchId=? and cardNo=?";
		prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(1, b.getBookId());
		prepareStatement.setInt(2, b.getBranchId());
		prepareStatement.setInt(3, b.getCardNo());
		prepareStatement.execute();
	}
	
	public void update(BookLoans b) throws SQLException
	{
		String sql = "Update library.tbl_book_loans set dueDate =? where bookId =? and branchId=? and cardNo=?";
		prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setDate(1, (Date) b.getDueDate());
		prepareStatement.setInt(2, b.getBookId());
		prepareStatement.setInt(3, b.getBranchId());
		prepareStatement.setInt(4, b.getCardNo());
		prepareStatement.execute();
	}
}
