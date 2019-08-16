package com.Anderson.DBLMS.Dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Anderson.DBLMS.Entity.Author;

public class AuthorDao extends Dao {
	Connection conn = getConnection();
	ResultSet author = null;
	PreparedStatement prepareStatement = null;
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public ResultSet getAll() 
	{
		
		String sql = "Select * From library.tbl_author";
		try {
			prepareStatement = conn.prepareStatement(sql);
			author = prepareStatement.executeQuery();
		} catch (SQLException e) {
			System.out.println("Could not retrieve authors");
		}
		return author;
	}
	
	public void add(Author a) throws SQLException
	{
		String sql = "insert into library.tbl_author (authorID, authorName) values (?,?)";
		prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(1, a.getAuthorId());
		prepareStatement.setString(2, a.getAuthorName());
		prepareStatement.execute();
	}
	
	public void remove(Author a) throws SQLException
	{
		String sql = "Delete From library.tbl_author where authorID=?";
		prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(1, a.getAuthorId());
		prepareStatement.execute();
	}
	
	public void update(Author a) throws SQLException
	{
		String sql = "Update library.tbl_author set authorName =? where authorId =?";
		prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(2, a.getAuthorId());
		prepareStatement.setString(1, a.getAuthorName());
		prepareStatement.execute();
	}
	

}
