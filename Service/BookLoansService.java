package com.Anderson.DBLMS.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import com.Anderson.DBLMS.Menu;
import com.Anderson.DBLMS.Dao.BookLoansDao;
import com.Anderson.DBLMS.Dao.Dao;
import com.Anderson.DBLMS.Entity.Book;
import com.Anderson.DBLMS.Entity.BookCopy;
import com.Anderson.DBLMS.Entity.BookLoans;
import com.Anderson.DBLMS.Entity.Borrower;
import com.Anderson.DBLMS.Entity.LibraryBranch;

public class BookLoansService {
	BookLoansDao blDao = new BookLoansDao();
	ResultSet result = null;
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public List<BookLoans> getAll()
	{
		List<BookLoans> blList = new ArrayList();
		result = blDao.getAll();
		try {
			while(result.next())
			{
				try {
					BookLoans temp = new BookLoans();
					temp.setBookId(result.getInt(1));
					temp.setBranchId(result.getInt(2));
					temp.setCardNo(result.getInt(3));
					temp.setDateOut(result.getDate(4));
					temp.setDueDate(result.getDate(5));
					blList.add(temp);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return blList;
	}
	
	public void Update() throws ParseException {
		System.out.println("Enter the Book Id number");
		List<BookLoans> blList = new ArrayList();
		BookLoans bl = new BookLoans();
		blList = getAll();
		int pos = -1;	
		try {
			int bkId = Integer.parseInt(in.readLine());
			System.out.println("Enter the Branch Id number");
			int brId = Integer.parseInt(in.readLine());
			System.out.println("Enter the card number");
			int card = Integer.parseInt(in.readLine());
			for(Iterator<BookLoans> i = blList.iterator(); i.hasNext();)
			{
				bl = i.next();
				if(bl.getBookId() == bkId && bl.getBranchId() == brId && bl.getCardNo() == card)
				{
					 pos = blList.indexOf(bl);
				}
			}
		
			if(pos >-1)
			{
			bl = blList.get(pos);
			System.out.println("Enter the new due date as yyyy-mm-dd or N/A or skip");
			String input = in.readLine();
			
			int count = 0;
			if(!input.equalsIgnoreCase("N/A"))
			{
				LocalDate ldate = LocalDate.parse(input);
				Date date = java.sql.Date.valueOf(ldate);
				bl.setDueDate(date);
				count++;
			}
			if(count >0)
			blDao.update(bl);
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
	
	public void checkOut(LibraryBranch lb, Borrower br) throws SQLException, NumberFormatException, IOException
	{
		Connection conn = Dao.getConnection();
		ResultSet books = null;
		PreparedStatement prepareStatement = null;
		String sql = "Select bk.title, au.authorName, bk.bookId\r\n" + 
				"From library.tbl_book as bk\r\n" + 
				"inner Join library.tbl_author as au on bk.authId = au.authorId\r\n" + 
				"Where bk.bookId = Any \r\n" +
				"(Select bc.bookId\r\n" + 
						"FROM library.tbl_book_copies as bc\r\n" +
						"Where bc.branchId = ? and bc.noOfCopies > 0)";
		int choice = 0;
		
		
			prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setInt(1, lb.getBranchId());
			books = prepareStatement.executeQuery();
			String test = books.toString();
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
					BookLoans bl = new BookLoans();
					books.absolute(choice);
					bl.setBookId(books.getInt(3));
					bl.setBranchId(lb.getBranchId());
					bl.setCardNo(br.getCardNo());
					LocalDate localDate = LocalDate.now(ZoneId.systemDefault());
					Date today = java.sql.Date.valueOf(localDate);
					bl.setDateOut(today);
					LocalDate dueDate = localDate.plusDays(7);
					Date due = java.sql.Date.valueOf(dueDate);
					bl.setDueDate(due);
					blDao.add(bl);
					
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
	
	public void returnBook(Borrower br, LibraryBranch lb) throws SQLException, NumberFormatException, IOException
	{
		Connection conn = Dao.getConnection();
		ResultSet books = null;
		PreparedStatement prepareStatement = null;
		String sql = "Select bk.title, au.authorName, bk.bookId\r\n" + 
				"From library.tbl_book as bk\r\n" + 
				"inner Join library.tbl_author as au on bk.authId = au.authorId\r\n" + 
				"Where bk.bookId = Any \r\n" +
				"(Select bl.bookId\r\n" + 
						"FROM library.tbl_book_loans as bl\r\n" +
						"Where bl.branchId = ? and bl.cardNo =?)";
		int choice = 0;
		prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(1, lb.getBranchId());
		prepareStatement.setInt(2, br.getCardNo());
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
				List<BookLoans> blList = new ArrayList();
				BookLoans bl = new BookLoans();
				blList = getAll();
				for(Iterator<BookLoans> i = blList.iterator(); i.hasNext();)
				{
					bl = i.next();
					if(bl.getBranchId() == lb.getBranchId() && bl.getBookId() == books.getInt(3)  && bl.getCardNo() == br.getCardNo())
					{
						 pos = blList.indexOf(bl);
					}
				}
				bl = blList.get(pos);
				blDao.remove(bl);
				
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

