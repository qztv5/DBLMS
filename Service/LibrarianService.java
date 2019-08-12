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
import com.Anderson.DBLMS.Entity.Book;
import com.Anderson.DBLMS.Entity.BookCopy;
import com.Anderson.DBLMS.Entity.BookLoans;
import com.Anderson.DBLMS.Entity.LibraryBranch;

public class LibrarianService {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	BookCopyService bcs = new BookCopyService();
	BookCopiesDao bcDao = new BookCopiesDao();
	
	public void addCopies(LibraryBranch lb) throws SQLException, NumberFormatException, IOException
	{
		Connection conn = Dao.getConnection();
		ResultSet books = null;
		PreparedStatement prepareStatement = null;
		String sql = "Select bk.title, au.authorName, bk.bookId\r\n" + 
				"From library.tbl_book as bk\r\n" + 
				"inner Join library.tbl_author as au on bk.authId = au.authorId\r\n";
		int choice = 0;
		prepareStatement = conn.prepareStatement(sql);
		books = prepareStatement.executeQuery();
		int count = 1;
		while(books.next())
		{	
			System.out.println(count + ") " + books.getString(1) + " by " + books.getString(2));
			count++;
		}
		System.out.println(count + ") Quit to previous");
		do {
			choice = Integer.parseInt(in.readLine());
			if(choice < count)
			{
				books.absolute(choice);
				int pos = -1;
				List<BookCopy> bcList = new ArrayList();
				BookCopy bc = new BookCopy();
				bcList = bcs.getAll();
				for(Iterator<BookCopy> i = bcList.iterator(); i.hasNext();)
				{
					bc = i.next();
					if(bc.getBranchId() == lb.getBranchId() && bc.getBookId() == books.getInt(3))
					{
						 pos = bcList.indexOf(bc);
					}
				}
				if(pos != -1)
				{
				bc = bcList.get(pos);
				System.out.println("Existing number of copies: " + bc.getNoOfCopies());
				System.out.print("Enter new number of copies: ");
				int copies = Integer.parseInt(in.readLine());
				bc.setNoOfCopies(copies);
				bcDao.update(bc);
				Menu libMenu = new Menu();
				libMenu.libMenu(lb);
				}
				else
				{
					System.out.println("Existing number of copies: 0");
					bc.setBookId(books.getInt(3));
					bc.setBranchId(lb.getBranchId());
					System.out.print("Enter new number of copies: ");
					int copies = Integer.parseInt(in.readLine());
					bc.setNoOfCopies(copies);
					bcDao.add(bc);
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
