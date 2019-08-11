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

import com.Anderson.DBLMS.Dao.BookDao;
import com.Anderson.DBLMS.Dao.Dao;
import com.Anderson.DBLMS.Entity.Book;
import com.Anderson.DBLMS.Entity.Borrower;

public class BookService {

	BookDao bDao = new BookDao();
	ResultSet result = null;
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public List<Book> getAll()
	{
		List<Book> bList = new ArrayList();
		
		result = bDao.getAll();
		try {
			while(result.next())
			{
				try {
					Book temp = new Book();
					temp.setBookId(result.getInt(1));
					temp.setTitle(result.getString(2));
					temp.setAuthId(result.getInt(3));
					temp.setPubId(result.getInt(4));
					bList.add(temp);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bList;
	}
	
	public void Add() {
		System.out.println("Enter the Book Name");
		Book b = new Book();
		try {
			b.setTitle(in.readLine());
			System.out.println("Enter the Book Id number");
			b.setBookId(Integer.parseInt(in.readLine()));
			System.out.println("Enter the author Id number");
			b.setAuthId(Integer.parseInt(in.readLine()));
			System.out.println("Enter the publisher Id number");
			b.setPubId(Integer.parseInt(in.readLine()));
			bDao.add(b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void Remove() {
		System.out.println("Enter the Book Name");
		Book b = new Book();
		try {
			b.setTitle(in.readLine());
			System.out.println("Enter the Book Id number");
			b.setBookId(Integer.parseInt(in.readLine()));
			bDao.remove(b);
		}catch(IOException e)
		{
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}


	public void Retrieve() throws IOException {
		System.out.println("Enter the Book Name to retreive a specific book");
		System.out.println("Enter all to retreive all books");
		List<Book> bList = getAll();
		String input = in.readLine();
			if(input.equalsIgnoreCase("all"))
			{
				bList.forEach(System.out::println);
			}
			for(Iterator<Book> i = bList.iterator(); i.hasNext();)
			{
				Book b = i.next();
				//System.out.println(b.getName());
				if(b.getTitle().equalsIgnoreCase(input))
				{
					System.out.println(b);
					return;
				}

			}	
		}

	public void Update() {
		System.out.println("Enter the Book Name");
		List<Book> bList = new ArrayList();
		Book b = new Book();
		bList = getAll();
		int pos = -1;
		String name = new String();	
		try {
			name = in.readLine();
			System.out.println("Enter the Book Id number");
			int id =Integer.parseInt(in.readLine());
			for(Iterator<Book> i = bList.iterator(); i.hasNext();)
			{
				b = i.next();
				if(b.getBookId() == id && b.getTitle().equals(name))
				{
					 pos = bList.indexOf(b);
				}
			}
		
			if(pos >-1)
			{
			b = bList.get(pos);
			System.out.println("Enter the new name or N/A or skip");
			String input = in.readLine();
			int count = 0;
			if(!input.equalsIgnoreCase("N/A"))
			{
				b.setTitle(input);
				count++;
			}
			if(count >0)
			bDao.update(b);
			else
			{
				System.out.println("Nothing was updated");
			}
			}
		}catch(IOException e)
		{
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
