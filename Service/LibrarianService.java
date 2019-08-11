package com.Anderson.DBLMS.Service;

import java.util.ArrayList;
import java.util.List;

import com.Anderson.DBLMS.Entity.Book;

public class LibrarianService {
	
	public void addCopies()
	{
		BookService bs = new BookService();
		final List<Book> bList = bs.getAll();
		bList.forEach((b) -> {
			System.out.println(bList.indexOf(b) +1 +") " + b.getTitle() +" by " +b.getAuthId());
			});
		int count = bList.size() + 1;
		System.out.println(count + ") Quit to previous");
	}

}
