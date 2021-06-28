package cn.jmu.mapper;

import java.util.List;

import cn.jmu.po.Author;

public interface AuthorMapper {
	
	public String getAuthorName(String authorID);
	
	public int addAuthor(Author author);
	
	public List<Author> findall();

}
