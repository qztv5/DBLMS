package com.Anderson.DBLMS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

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
				
				break;
			case 3:
				
				break;
			default:
				System.out.println("The number was not between 1 and 3 please enter a new number");
			}
			
	}while(choice <1 || choice >3);
}
	public void librarianMenu()
	{
		System.out.println("1) Enter Branch you manage");
		System.out.println("2) Quit to previous");
		int choice = 0;
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
			mainMenu();
			break;
			default:
			System.out.println("The number was not 1 or 2");
		}
		}
	}

