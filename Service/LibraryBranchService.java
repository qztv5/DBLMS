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

import com.Anderson.DBLMS.Dao.LibraryBranchDao;
import com.Anderson.DBLMS.Dao.Dao;
import com.Anderson.DBLMS.Entity.LibraryBranch;

public class LibraryBranchService {

	LibraryBranchDao lbDao = new LibraryBranchDao();
	Dao dao = new Dao();
	Connection conn = null;
	ResultSet result = null;
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public List<LibraryBranch> getAll()
	{
		List<LibraryBranch> lbList = new ArrayList();
		
		result = lbDao.getAll();
		try {
			while(result.next())
			{
				try {
					LibraryBranch temp = new LibraryBranch();
					temp.setBranchId(result.getInt(1));
					temp.setBranchName(result.getString(2));
					temp.setBranchAddress(result.getString(3));
					lbList.add(temp);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lbList;
	}
	
	public void Add() {
		System.out.println("Enter the LibraryBranch Name");
		LibraryBranch lb = new LibraryBranch();
		try {
			lb.setBranchName(in.readLine());
			System.out.println("Enter the Library Branch Id number");
			lb.setBranchId(Integer.parseInt(in.readLine()));
			System.out.println("Enter the Library Branch Address");
			lb.setBranchAddress(in.readLine());
			lbDao.add(lb);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void Remove() {
		System.out.println("Enter the LibraryBranch Name");
		LibraryBranch lb = new LibraryBranch();
		try {
			lb.setBranchName(in.readLine());
			System.out.println("Enter the LibraryBranch Id number");
			lb.setBranchId(Integer.parseInt(in.readLine()));
			lbDao.remove(lb);
		}catch(IOException e)
		{
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}


	public void Retrieve() throws IOException {
		System.out.println("Enter the LibraryBranch Name to retreive a specific branch");
		System.out.println("Enter all to retreive all branches");
		List<LibraryBranch> lbList = getAll();
		String input = in.readLine();
			if(input.equalsIgnoreCase("all"))
			{
				lbList.forEach(System.out::println);
			}
			for(Iterator<LibraryBranch> i = lbList.iterator(); i.hasNext();)
			{
				LibraryBranch lb = i.next();
				//System.out.println(a.getName());
				if(lb.getBranchName().equalsIgnoreCase(input))
				{
					System.out.println(lb);
					return;
				}

			}	
		}

	public void Update() {
		System.out.println("Enter the LibraryBranch Name");
		List<LibraryBranch> lbList = new ArrayList();
		LibraryBranch lb = new LibraryBranch();
		lbList = getAll();
		try {
			lb.setBranchName(in.readLine());
			System.out.println("Enter the LibraryBranch Id number");
			lb.setBranchId(Integer.parseInt(in.readLine()));
			if(lbList.contains(lb))
			{
			System.out.println("Enter the new name or N/A to skip");
			String input = in.readLine();
			int count = 0;
			if(!input.equalsIgnoreCase("N/A"))
			{
				lb.setBranchName(input);
				count++;
			}
			System.out.println("Enter the new address or N/A to skip");
			input = in.readLine();
			if(!input.equalsIgnoreCase("N/A"))
			{
				lb.setBranchAddress(input);
				count++;
			}
			if(count >0)
			lbDao.update(lb);
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

