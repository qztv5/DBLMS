package com.Anderson.DBLMS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Menu {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
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
				// TODO Auto-generated catch block
				e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		System.out.println("1) University Library, Boston");
		System.out.println("2) State Library, New York");
		System.out.println("3) Federal Library, Washington DC");
		System.out.println("4) County Library, McLean VA");
		System.out.println("5) Quit to previous");
		
		int choice = 0;
		do {
		try {
			choice = Integer.parseInt(in.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch (choice)
		{
		case 1:
		case 2:
		case 3:
		case 4:
			libMenu();
			break;
		case 5:
			librarianMenu();
			break;
			default:
				System.out.println("The number was not between 1 and 5 enter a correct number");
		}
		}while(choice <1 || choice >5);
	}
	
	 public void libMenu()
	 {
		 System.out.println("1) Update the details of the Library");
		 System.out.println("2) Add copies of Book to the Branch");
		 System.out.println("3) Quit to previous");
		 int choice = 0;
			do {
			try {
				choice = Integer.parseInt(in.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch (choice)
			{
			case 1:
				break;
			case 2:
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
					// TODO Auto-generated catch block
					e.printStackTrace();
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				switch (choice)
				{
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				switch (choice)
				{
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				switch (choice)
				{
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				switch (choice)
				{
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
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
		 do {
		 try {
			 id = Integer.parseInt(in.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 }while(id <1 || id >1000000);
		 
		 System.out.println("1) Check out a book");
		 System.out.println("2) Return a book");
		 System.out.println("3) Quit to previous");
		 int choice = 0;
		 do {
				try {
					choice = Integer.parseInt(in.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				switch (choice)
				{
				case 1:
					break;
				case 2:
					break;
				case 3:
					mainMenu();
					break;
					default:
						System.out.println("The number was not between 1 and 3 enter a correct number");
				}
				}while(choice <1 || choice >3);
		 
	 }
	}

