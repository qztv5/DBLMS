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

import com.Anderson.DBLMS.Dao.BorrowerDao;
import com.Anderson.DBLMS.Dao.Dao;
import com.Anderson.DBLMS.Entity.Borrower;

public class BorrowerService {
	
	BorrowerDao bDao = new BorrowerDao();
	Dao dao = new Dao();
	Connection conn = null;
	ResultSet result = null;
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public List<Borrower> getAll()
	{
		List<Borrower> bList = new ArrayList();
		
		result = bDao.getAll();
		try {
			while(result.next())
			{
				try {
					Borrower temp = new Borrower();
					temp.setCardNo(result.getInt(1));
					temp.setName(result.getString(2));
					temp.setAddress(result.getString(3));
					temp.setPhone(result.getString(4));
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
		System.out.println("Enter the Borrower Name");
		Borrower b = new Borrower();
		try {
			b.setName(in.readLine());
			System.out.println("Enter the Id number");
			b.setCardNo(Integer.parseInt(in.readLine()));
			System.out.println("Enter the address");
			b.setAddress(in.readLine());
			System.out.println("Enter the phone number");
			b.setPhone(in.readLine());
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
		System.out.println("Enter the Borrower Name");
		Borrower b = new Borrower();
		try {
			b.setName(in.readLine());
			System.out.println("Enter the Borrower Id number");
			b.setCardNo(Integer.parseInt(in.readLine()));
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
		System.out.println("Enter the Borrower Name to retreive a specific author");
		System.out.println("Enter all to retreive all authors");
		List<Borrower> bList = getAll();
		String input = in.readLine();
			if(input.equalsIgnoreCase("all"))
			{
				bList.forEach(System.out::println);
			}
			for(Iterator<Borrower> i = bList.iterator(); i.hasNext();)
			{
				Borrower a = i.next();
				//System.out.println(a.getName());
				if(a.getName().equalsIgnoreCase(input))
				{
					System.out.println(a);
					return;
				}

			}	
		}

	public void Update() {
		System.out.println("Enter the Borrower Name");
		List<Borrower> bList = new ArrayList();
		Borrower b = new Borrower();
		bList = getAll();
		try {
			b.setName(in.readLine());
			System.out.println("Enter the Borrower Id number");
			b.setCardNo(Integer.parseInt(in.readLine()));
			if(bList.contains(b))
			{
			System.out.println("Enter the new name or N/A to skip");
			String input = in.readLine();
			int count = 0;
			if(!input.equalsIgnoreCase("N/A"))
			{
				b.setName(input);
				count++;
			}
			System.out.println("Enter the new address or N/A to skip");
			input = in.readLine();
			if(!input.equalsIgnoreCase("N/A"))
			{
				b.setAddress(input);
				count++;
			}
			System.out.println("Enter the new phone or N/A to skip");
			input = in.readLine();
			if(!input.equalsIgnoreCase("N/A"))
			{
				b.setPhone(input);
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
