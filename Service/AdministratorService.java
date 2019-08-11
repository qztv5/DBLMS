package com.Anderson.DBLMS.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;




public class AdministratorService {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	ResultSet results = null;
	AuthorService au = new AuthorService();
	PublisherService ps = new PublisherService();
	BookService bs = new BookService();
	LibraryBranchService lbs = new LibraryBranchService();
	BorrowerService brs = new BorrowerService();
	
	public void addBook()
	{
		bs.Add();
	}
	public void updateBook()
	{
		bs.Update();
	}
	public void deleteBook()
	{
		bs.Remove();
	}
	public void getBook()
	{
		try {
			bs.Retrieve();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addAuthor()
	{
		au.Add();
	}
	public void updateAuthor()
	{
		au.Update();
	}
	public void deleteAuthor()
	{
		au.Remove();
	}
	public void getAuthor()
	{
		try {
			au.Retrieve();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addPublisher()
	{
		ps.Add();
	}
	public void updatePublisher() 
	{
		ps.Update();
	}
	public void deletePublisher()
	{
		ps.Remove();
	}
	public void getPublisher()
	{
		try {
			ps.Retrieve();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addLibraryBranch()
	{
		lbs.Add();
	}
	public void updateLibraryBrach()
	{
		lbs.Update();
	}
	public void deleteLibraryBranch()
	{
		lbs.Remove();
	}
	public void getLibraryBranch()
	{
		try {
			lbs.Retrieve();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addBorrower()
	{
		brs.Add();
	}
	public void updateBorrower()
	{
		brs.Update();
	}
	public void deleteBorrower()
	{
		brs.Remove();
	}
	public void getBorrower()
	{
		try {
			brs.Retrieve();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void OverrideDueDate()
	{
		
	}

}
