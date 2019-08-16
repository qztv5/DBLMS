package com.Anderson.DBLMS.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.Anderson.DBLMS.Dao.AuthorDao;
import com.Anderson.DBLMS.Dao.Dao;
import com.Anderson.DBLMS.Entity.Author;



public class AuthorService {
	AuthorDao aDao = new AuthorDao();
	Dao dao = new Dao();
	Connection conn = null;
	ResultSet result = null;
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public List<Author> getAll()
	{
		List<Author> aList = new ArrayList<Author>();
		
		result = aDao.getAll();
		try {
			while(result.next())
			{
				try {
					Author temp = new Author();
					temp.setAuthorId(result.getInt(1));
					temp.setAuthorName(result.getString(2));
					aList.add(temp);
				} catch (SQLException e) {
					System.out.println("Error reading from database");
				}
			}
		} catch (SQLException e) {
			System.out.println("Error reading from database");
		}
		return aList;
	}
	
	public void Add() {
		System.out.println("Enter the Author Name");
		Author a = new Author();
		try {
			a.setAuthorName(in.readLine());
			System.out.println("Enter the Author Id number");
			a.setAuthorId(0);
			do {
				try {
			a.setAuthorId(Integer.parseInt(in.readLine()));
				}  catch (NumberFormatException e) {
					System.out.println("Please enter an int");
				}
			}while(a.getAuthorId()==0);
			aDao.add(a);
		} catch (IOException e) {
			System.out.println("There was an input or output error");
		} catch (SQLException e) {
			System.out.println("Error reading from database");
		}
	}


	public void Remove() {
		System.out.println("Enter the Author Name");
		Author a = new Author();
		try {
			a.setAuthorName(in.readLine());
			System.out.println("Enter the Author Id number");
			a.setAuthorId(0);
			do {
				try {
			a.setAuthorId(Integer.parseInt(in.readLine()));
				}  catch (NumberFormatException e) {
					System.out.println("Please enter an int");
				}
			}while(a.getAuthorId()==0);
			aDao.remove(a);
		}catch(IOException e)
		{
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	
	}


	public void Retrieve() {
		System.out.println("Enter the Author Name to retreive a specific author");
		System.out.println("Enter all to retreive all authors");
		List<Author> aList = getAll();
		String input;
		try {
			input = in.readLine();
		
			if(input.equalsIgnoreCase("all"))
			{
				aList.forEach(System.out::println);
			}
			for(Iterator<Author> i = aList.iterator(); i.hasNext();)
			{
				Author a = i.next();
				//System.out.println(a.getName());
				if(a.getAuthorName().equalsIgnoreCase(input))
				{
					System.out.println(a);
					return;
				}
			}
			} catch (IOException e) {
				System.out.println("There was an input or output error");
			}	
		}

	public void Update() {
		System.out.println("Enter the Author Name");
		List<Author> aList = new ArrayList<Author>();
		Author a = new Author();
		aList = getAll();
		try {
			a.setAuthorName(in.readLine());
			System.out.println("Enter the Author Id number");
			a.setAuthorId(0);
			do {
				try
				{
			a.setAuthorId(Integer.parseInt(in.readLine()));
				}  catch (NumberFormatException e) {
					System.out.println("Please enter an int");
				}
			}while(a.getAuthorId() ==0);
			if(aList.contains(a))
			{
			System.out.println("Enter the new name or N/A or skip");
			String input = in.readLine();
			int count = 0;
			if(!input.equalsIgnoreCase("N/A"))
			{
				a.setAuthorName(input);
				count++;
			}
			if(count >0)
			aDao.update(a);
			else
			{
				System.out.println("Nothing was updated");
			}
			}
			
		} catch (IOException e) {
			System.out.println("There was an input or output error");
		} catch (SQLException e) {
			System.out.println("There was an error writing to the database");
		}
		
	}
}
