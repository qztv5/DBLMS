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

import com.Anderson.DBLMS.Dao.PublisherDao;
import com.Anderson.DBLMS.Entity.Publisher;
import com.Anderson.DBLMS.Dao.Dao;

public class PublisherService {
	
	PublisherDao pDao = new PublisherDao();
	Dao dao = new Dao();
	Connection conn = null;
	ResultSet result = null;
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public List<Publisher> getAll()
	{
		List<Publisher> pList = new ArrayList();
		
		result = pDao.getAll();
		try {
			while(result.next())
			{
				try {
					Publisher temp = new Publisher();
					temp.setPublisherId(result.getInt(1));
					temp.setPublisherName(result.getString(2));
					temp.setPublisherAddress(result.getString(3));
					temp.setPublisherPhone(result.getString(4));
					pList.add(temp);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pList;
	}
	
	public void Add() {
		System.out.println("Enter the Publisher Name");
		Publisher p = new Publisher();
		try {
			p.setPublisherName(in.readLine());
			System.out.println("Enter the Id number");
			p.setPublisherId(Integer.parseInt(in.readLine()));
			System.out.println("Enter the address");
			p.setPublisherAddress(in.readLine());
			System.out.println("Enter the phone number as xxx-xxx-xxxx");
			p.setPublisherPhone(in.readLine());
			pDao.add(p);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void Remove() {
		System.out.println("Enter the Publisher Name");
		Publisher p = new Publisher();
		try {
			p.setPublisherName(in.readLine());
			System.out.println("Enter the Publisher Id number");
			p.setPublisherId(Integer.parseInt(in.readLine()));
			pDao.remove(p);
		}catch(IOException e)
		{
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}


	public void Retrieve() throws IOException {
		System.out.println("Enter the Publisher Name to retreive a specific author");
		System.out.println("Enter all to retreive all authors");
		List<Publisher> pList = getAll();
		String input = in.readLine();
			if(input.equalsIgnoreCase("all"))
			{
				pList.forEach(System.out::println);
			}
			for(Iterator<Publisher> i = pList.iterator(); i.hasNext();)
			{
				Publisher p = i.next();
				//System.out.println(a.getName());
				if(p.getPublisherName().equalsIgnoreCase(input))
				{
					System.out.println(p);
					return;
				}

			}	
		}

	public void Update() {
		System.out.println("Enter the Publisher Name");
		List<Publisher> pList = new ArrayList();
		Publisher p = new Publisher();
		pList = getAll();
		try {
			p.setPublisherName(in.readLine());
			System.out.println("Enter the Id number");
			p.setPublisherId(Integer.parseInt(in.readLine()));
			System.out.println("Enter the address");
			p.setPublisherAddress(in.readLine());
			System.out.println("Enter the phone number as xxx-xxx-xxxx");
			p.setPublisherPhone(in.readLine());
			if(pList.contains(p))
			{
				System.out.println("Enter the new name or N/A or skip");
				String input = in.readLine();
				int count = 0;
				if(!input.equalsIgnoreCase("N/A"))
				{
					p.setPublisherName(input);
					count++;
				}
				System.out.println("Enter the new address or N/A or skip");
				input = in.readLine();
				if(!input.equalsIgnoreCase("N/A"))
				{
					p.setPublisherAddress(input);
					count++;
				}
				System.out.println("Enter the new phone number or N/A or skip");
				input = in.readLine();
				if(!input.equalsIgnoreCase("N/A"))
				{
					p.setPublisherPhone(input);
					count++;
				}
				if(count >0)
				pDao.update(p);
				else
				{
					System.out.println("Nothing was updated");
				}
				
			pDao.update(p);
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