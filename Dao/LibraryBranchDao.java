package com.Anderson.DBLMS.Dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Anderson.DBLMS.Entity.LibraryBranch;

public class LibraryBranchDao extends Dao {
	Connection conn = getConnection();
	ResultSet librarybranch = null;
	PreparedStatement prepareStatement = null;
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public ResultSet getAll() 
	{
		
		String sql = "Select * From library.tbl_library_branch";
		try {
			prepareStatement = conn.prepareStatement(sql);
			librarybranch = prepareStatement.executeQuery();
		} catch (SQLException e) {
			System.out.println("Could not retrieve library branches");
		}
		return librarybranch;
	}
	
	public void add(LibraryBranch lb) throws SQLException
	{
		String sql = "insert into library.tbl_library_branch (branchID, branchName, branchAddress) values (?,?,?)";
		prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(1, lb.getBranchId());
		prepareStatement.setString(2, lb.getBranchName());
		prepareStatement.setString(3, lb.getBranchAddress());
		prepareStatement.execute();
	}
	
	public void remove(LibraryBranch lb) throws SQLException
	{
		String sql = "Delete From library.tbl_library_branch where branchID=?";
		prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(1, lb.getBranchId());
		prepareStatement.execute();
	}
	
	public void update(LibraryBranch lb) throws SQLException
	{
		String sql = "Update library.tbl_library_branch set branchName =?, branchAddress=? where branchId =?";
		prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(3, lb.getBranchId());
		prepareStatement.setString(2, lb.getBranchAddress());
		prepareStatement.setString(1, lb.getBranchName());
		prepareStatement.execute();
	}
}