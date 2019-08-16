package com.Anderson.DBLMS.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Anderson.DBLMS.Dao.BookCopiesDao;
import com.Anderson.DBLMS.Entity.BookCopy;


public class BookCopyService {
	BookCopiesDao bcDao = new BookCopiesDao();
	ResultSet result = null;
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public List<BookCopy> getAll()
	{
		List<BookCopy> bcList = new ArrayList<BookCopy>();
		result = bcDao.getAll();
		try {
			while(result.next())
			{
				try {
					BookCopy temp = new BookCopy();
					temp.setBookId(result.getInt(1));
					temp.setBranchId(result.getInt(2));
					temp.setNoOfCopies(result.getInt(3));
					bcList.add(temp);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bcList;
	}

}
