package cn.jmu.service;

import java.util.List;

import cn.jmu.po.Author;

public interface AuthorService {
	
public String getAuthorName(String authorID);
	
	public int addAuthor(Author author);

	public List<Author> findall();
}
