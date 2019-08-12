package com.Anderson.DBLMS.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.Anderson.DBLMS.Menu;
import com.Anderson.DBLMS.Dao.BookCopiesDao;
import com.Anderson.DBLMS.Dao.Dao;
import com.Anderson.DBLMS.Entity.BookCopy;
import com.Anderson.DBLMS.Entity.LibraryBranch;

public class LibrarianService {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	BookCopyService bcs = new BookCopyService();
	BookCopiesDao bcDao = new BookCopiesDao();
	
	public void addCopies(LibraryBranch lb)
	{
		Connection conn = Dao.getConnection();
		ResultSet books = null;
		PreparedStatement prepareStatement = null;
		String sql = "Select bk.title, au.authorName, bk.bookId\r\n" + 
				"From library.tbl_book as bk\r\n" + 
				"inner Join library.tbl_author as au on bk.authId = au.authorId\r\n";
		int choice = 0;
		int count = 1;
		try {
			prepareStatement = conn.prepareStatement(sql);
		
		books = prepareStatement.executeQuery();
		
		while(books.next())
		{	
			System.out.println(count + ") " + books.getString(1) + " by " + books.getString(2));
			count++;
		}
		} catch (SQLException e) {
			System.out.println("There was an error acesssing the database");
		}
		System.out.println(count + ") Quit to previous");
		do {
			try {
				choice = Integer.parseInt(in.readLine());
			} catch (NumberFormatException e) {
				System.out.println("Please enter an int");
			} catch (IOException e) {
				System.out.println("There was an input or output error");

			}
		}while(choice ==0);
		do {
			if(choice < count)
			{
				int pos = -1;
				List<BookCopy> bcList = new ArrayList();
				BookCopy bc = new BookCopy();
				try {
					books.absolute(choice);
					bcList = bcs.getAll();
				for(Iterator<BookCopy> i = bcList.iterator(); i.hasNext();)
				{
					bc = i.next();
					if(bc.getBranchId() == lb.getBranchId() && bc.getBookId() == books.getInt(3))
					{
						 pos = bcList.indexOf(bc);
					}
				}
				} catch (SQLException e1) {
					System.out.println("There was a database error");
				}
				if(pos != -1)
				{
				bc = bcList.get(pos);
				System.out.println("Existing number of copies: " + bc.getNoOfCopies());
				System.out.print("Enter new number of copies: ");
				int copies = 0;
				do {
				try {
				copies = Integer.parseInt(in.readLine());
				bc.setNoOfCopies(copies);
				} catch (NumberFormatException e) {
					System.out.println("Please enter an int");
				} catch (IOException e) {
					System.out.println("There was an input or output error");

				}
				}while(copies == 0);
				
				try {
					bcDao.update(bc);
				} catch (SQLException e) {
					System.out.println("Error writing to the database");
				}
				Menu libMenu = new Menu();
				libMenu.libMenu(lb);
				}
				else
				{
					System.out.println("Existing number of copies: 0");
					int copies =0;
					do {
					try {
						bc.setBookId(books.getInt(3));
					} catch (SQLException e) {
						System.out.println("Error reading from the database");
					}
					bc.setBranchId(lb.getBranchId());
					System.out.print("Enter new number of copies: ");
					
					try {
						copies = Integer.parseInt(in.readLine());
					} catch (NumberFormatException | IOException e) {
						System.out.println("Please enter an int");
					}
					}while(copies == 0);
					bc.setNoOfCopies(copies);
					try {
						bcDao.add(bc);
					} catch (SQLException e) {
						System.out.println("Error writing to the database");
					}
					Menu libMenu = new Menu();
					libMenu.libMenu(lb);
				}
				
			}
			else if(choice == count)
			{
				Menu borrower = new Menu();
				borrower.borrowerMenu();
			}
			else
				System.out.println("The number was not between 1 and "+ count + " enter a correct number");
		}while(choice < 1 || choice > count);
	}
	

}
