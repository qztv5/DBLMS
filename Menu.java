package com.Anderson.DBLMS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.Anderson.DBLMS.Entity.Borrower;
import com.Anderson.DBLMS.Entity.LibraryBranch;
import com.Anderson.DBLMS.Service.AdministratorService;
import com.Anderson.DBLMS.Service.BookLoansService;
import com.Anderson.DBLMS.Service.BorrowerService;
import com.Anderson.DBLMS.Service.LibrarianService;
import com.Anderson.DBLMS.Service.LibraryBranchService;



public class Menu {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	AdministratorService as = new AdministratorService();
	BorrowerService bs = new BorrowerService();
	LibraryBranchService lbs = new LibraryBranchService();
	LibrarianService ls = new LibrarianService();
	BookLoansService bls = new BookLoansService();
	
	public void mainMenu()
	{
		int choice =0;
		System.out.println("Welcome to the GCIT Library Managemnt System. Which category of a user are you\n");
		System.out.println("1) Librarian");
		System.out.println("2) Administrator");
		System.out.println("3) Borrower");
		do {
		
			try {
				choice = Integer.parseInt(in.readLine());
			} catch (IOException e) {
				System.out.println("Error with input or output");
			}catch (NumberFormatException e)
			{
				System.out.println("You did not enter an integer");
				mainMenu();
			}
			
				switch (choice)
			{
			case 1:
				librarianMenu();
				break;
			case 2:
				adminMenu();
				break;
			case 3:
				borrowerMenu();
				break;
			default:
				System.out.println("The number was not between 1 and 3 enter a correct number");
			}
			
	}while(choice <1 || choice >3);
}
	public void librarianMenu()
	{
		System.out.println("1) Enter Branch you manage");
		System.out.println("2) Quit to previous");
		int choice = 0;
		do {
		try {
			choice = Integer.parseInt(in.readLine());
		} catch (IOException e) {
			System.out.println("Error with input or output");
		}catch (NumberFormatException e)
		{
			System.out.println("You did not enter an integer");
			librarianMenu();
		}
		switch (choice)
		{
		case 1:
			libraryMenu();
			break;
		case 2:
			mainMenu();
			break;
			default:
			System.out.println("The number was not 1 or 2 enter a correct number");
		}
		}while(choice != 1 || choice !=2);
		}
	
	public void libraryMenu()
	{
		final List<LibraryBranch> lbList = lbs.getAll();
		int count = lbList.size() + 1;
		lbs.print(lbList, count);
		int choice = 0;
		do {
		try {
			choice = Integer.parseInt(in.readLine());
		} catch (NumberFormatException | IOException e) {
			System.out.println("Please enter an int");
		}
		}while(choice == 0);
		do {
		if(choice < count)
		{
			LibraryBranch lb = new LibraryBranch();
			lb = lbList.get(choice-1);
			libMenu(lb);
		}
		else if(choice == count)
		{
			librarianMenu();
		}
		else
			System.out.println("The number was not between 1 and "+ count + " enter a correct number");
		
		}while(choice <1 || choice > count);
	}
	
	 public void libMenu(LibraryBranch lb)
	 {
		 System.out.println("1) Update the details of the Library");
		 System.out.println("2) Add copies of Book to the Branch");
		 System.out.println("3) Quit to previous");
		 int choice = 0;
			do {
			try {
				choice = Integer.parseInt(in.readLine());
			} catch (IOException e) {
				System.out.println("Error with input or output");
			}catch (NumberFormatException e)
			{
				System.out.println("You did not enter an integer");
				libMenu(lb);
			}
			switch (choice)
			{
			case 1:
				lbs.Update(lb);
				break;
			case 2:
				ls.addCopies(lb);
				break;
			case 3:
				libraryMenu();
				break;
				default:
					System.out.println("The number was not between 1 and 3 enter a correct number");
			}
			}while(choice <1 || choice >3);
		 
	 }
	 
	 public void adminMenu()
	 {
		 	int choice =0;
			System.out.println("1) Add something to the database");
			System.out.println("2) Remove something from the database");
			System.out.println("3) Retrieve something from the database");
			System.out.println("4) Update something in the database");
			System.out.println("5) Override due date for a book loan");
			System.out.println("6) Quit to previous");
			do {
				try {
					choice = Integer.parseInt(in.readLine());
				} catch (IOException e) {
					System.out.println("Error with input or output");
				}catch (NumberFormatException e)
				{
					System.out.println("You did not enter an integer");
					adminMenu();
				}
				switch (choice)
				{
				case 1:
					addMenu();
					break;
				case 2:
					deleteMenu();
					break;
				case 3:
					retrieveMenu();
					break;
				case 4:
					updateMenu();
					break;
				case 5:
					as.OverrideDueDate();
					adminMenu();
					break;
				case 6:
					mainMenu();
					break;
					default:
						System.out.println("The number was not between 1 and 6 enter a correct number");
				}
				}while(choice <1 || choice >6);
			
	 }
	 
	 public void addMenu() 
	 {
		 System.out.println("1) Add a Book");
		 System.out.println("2) Add an Author");
		 System.out.println("3) Add a Publisher");
		 System.out.println("4) Add a Library Branch");
		 System.out.println("5) Add a Borrower");
		 System.out.println("6) Quit to previous");
		 int choice = 0;
		 do {
				try {
					choice = Integer.parseInt(in.readLine());
				} catch (IOException e) {
					System.out.println("Error with input or output");
				}catch (NumberFormatException e)
				{
					System.out.println("You did not enter an integer");
					addMenu();
				}
				switch (choice)
				{
				case 1:
					as.addBook();
					break;
				case 2:
					as.addAuthor();
					break;
				case 3:
					as.addPublisher();
					break;
				case 4:
					as.addLibraryBranch();
					break;
				case 5:
					as.addBorrower();
					break;
				case 6:
					adminMenu();
					default:
						System.out.println("The number was not between 1 and 6 enter a correct number");
				}
				}while(choice <1 || choice >6);
	 }
	 
	 public void updateMenu() 
	 {
		 System.out.println("1) Update a Book");
		 System.out.println("2) Update an Author");
		 System.out.println("3) Update a Publisher");
		 System.out.println("4) Update a Library Branch");
		 System.out.println("5) Update a Borrower");
		 System.out.println("6) Quit to previous");
		 int choice = 0;
		 do {
				try {
					choice = Integer.parseInt(in.readLine());
				} catch (IOException e) {
					System.out.println("Error with input or output");
				}catch (NumberFormatException e)
				{
					System.out.println("You did not enter an integer");
					updateMenu();
				}
				switch (choice)
				{
				case 1:
					as.updateBook();
					break;
				case 2:
					as.updateAuthor();
					break;
				case 3:
					as.updatePublisher();
					break;
				case 4:
					as.updateLibraryBrach();
					break;
				case 5:
					as.updateBorrower();
					break;
				case 6:
					adminMenu();
					default:
						System.out.println("The number was not between 1 and 6 enter a correct number");
				}
				}while(choice <1 || choice >6);
	 }
	 
	 public void deleteMenu() 
	 {
		 System.out.println("1) Delete a Book");
		 System.out.println("2) Delete an Author");
		 System.out.println("3) Delete a Publisher");
		 System.out.println("4) Delete a Library Branch");
		 System.out.println("5) Delete a Borrower");
		 System.out.println("6) Quit to previous");
		 int choice = 0;
		 do {
				try {
					choice = Integer.parseInt(in.readLine());
				} catch (IOException e) {
					System.out.println("Error with input or output");
				}catch (NumberFormatException e)
				{
					System.out.println("You did not enter an integer");
					deleteMenu();
				}
				switch (choice)
				{
				case 1:
					as.deleteBook();
					break;
				case 2:
					as.deleteAuthor();
					break;
				case 3:
					as.deletePublisher();
					break;
				case 4:
					as.deleteLibraryBranch();
					break;
				case 5:
					as.deleteBorrower();
					break;
				case 6:
					adminMenu();
					default:
						System.out.println("The number was not between 1 and 6 enter a correct number");
				}
				}while(choice <1 || choice >6);
	 }
	 
	 public void retrieveMenu() 
	 {
		 System.out.println("1) Retrieve a Book");
		 System.out.println("2) Retrieve an Author");
		 System.out.println("3) Retrieve a Publisher");
		 System.out.println("4) Retrieve a Library Branch");
		 System.out.println("5) Retrieve a Borrower");
		 System.out.println("6) Quit to previous");
		 int choice = 0;
		 do {
				try {
					choice = Integer.parseInt(in.readLine());
				} catch (IOException e) {
					System.out.println("Error with input or output");
				}catch (NumberFormatException e)
				{
					System.out.println("You did not enter an integer");
					retrieveMenu();
				}
				switch (choice)
				{
				case 1:
					as.getBook();
					adminMenu();
					break;
				case 2:
					as.getAuthor();
					adminMenu();
					break;
				case 3:
					as.getPublisher();
					adminMenu();
					break;
				case 4:
					as.getLibraryBranch();
					adminMenu();
					break;
				case 5:
					as.getBorrower();
					adminMenu();
					break;
				case 6:
					adminMenu();
					default:
						System.out.println("The number was not between 1 and 6 enter a correct number");
				}
				}while(choice <1 || choice >6);
	 }
	 
	 public void borrowerMenu()
	 {
		 System.out.print("Enter your card number: ");
		 int id = 0;
		 List<Borrower> bList =bs.getAll();
		 Borrower b = new Borrower();
		 int pos = -1;
		 do {
		 try {
			 id = Integer.parseInt(in.readLine());
			 for(Iterator<Borrower> i = bList.iterator(); i.hasNext();)
				{
					b = i.next();
					if(b.getCardNo() == id)
					{
						 pos = bList.indexOf(b);
					}
				}
			 if(pos == -1)
			 {
				 System.out.println("Card Number not found enter a valid one");
			 }
			 else{
				 b = bList.get(pos);
			 }
		} catch (IOException e) {
			System.out.println("Invalid input or output");
		}
		 }while(pos == -1);
		 int choice = 0;
		 do {
		 System.out.println("1) Check out a book");
		 System.out.println("2) Return a book");
		 System.out.println("3) Quit to previous");
		 
				try {
					choice = Integer.parseInt(in.readLine());
				} catch (IOException e) {
					System.out.println("Error with input or output");
				}catch (NumberFormatException e)
				{
					System.out.println("You did not enter an integer");
				}
		 }while(choice ==0);
		 do {
				switch (choice)
				{
				case 1:
					checkOutMenu(b);
					break;
				case 2:
					returnMenu(b);
					break;
				case 3:
					mainMenu();
					break;
					default:
						System.out.println("The number was not between 1 and 3 enter a correct number");
				}
				
				}while(choice <1 || choice >3);
		 
	 }
	public void returnMenu(Borrower b) {
		System.out.println("Pick the branch you want to return a book to");
		final List<LibraryBranch> lbList = lbs.getAll();
		int count = lbList.size() + 1;
		lbs.print(lbList, count);
		int choice = 0;
		do {
		try {
			choice = Integer.parseInt(in.readLine());
		} catch (IOException e) {
			System.out.println("Error with input or output");
		}catch (NumberFormatException e)
		{
			System.out.println("You did not enter an integer");
		}
		}while(choice ==0);
		do {
		if(choice < count)
		{
			LibraryBranch lb = new LibraryBranch();
			lb = lbList.get(choice-1);
				bls.returnBook(b, lb);
				returnMenu(b);
		}
		else if(choice == count)
		{
			borrowerMenu();
		}
		else
			System.out.println("The number was not between 1 and "+ count + " enter a correct number");
		
		}while(choice <1 || choice > count);
		
	}
	public void checkOutMenu(Borrower b) {
		System.out.println("Pick the branch you want to check out from");
		final List<LibraryBranch> lbList = lbs.getAll();
		int count = lbList.size() + 1;
		lbs.print(lbList, count);
		int choice = 0;
		do {
		try {
			choice = Integer.parseInt(in.readLine());
		} catch (IOException e) {
			System.out.println("Please enter an int");
		}
		}while(choice ==0);
		do {
		if(choice < count)
		{
			LibraryBranch lb = new LibraryBranch();
			lb = lbList.get(choice-1);
				bls.checkOut(lb, b);
				checkOutMenu(b);
		}
		else if(choice == count)
		{
			borrowerMenu();
		}
		else
			System.out.println("The number was not between 1 and "+ count + " enter a correct number");
		
		}while(choice <1 || choice > count);
	}
		
}

