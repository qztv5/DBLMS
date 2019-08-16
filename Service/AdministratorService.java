package com.Anderson.DBLMS.Service;

import java.io.BufferedReader;
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
	BookLoansService bls = new BookLoansService();
	
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
			bs.Retrieve();
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
			au.Retrieve();
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
			ps.Retrieve();
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
			lbs.Retrieve();
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
			brs.Retrieve();
	}
	public void OverrideDueDate()
	{
			bls.Update();
	}

}
