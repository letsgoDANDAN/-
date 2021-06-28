package cn.jmu.mapper;

import java.util.List;

import cn.jmu.po.BookType;

public interface BookTypeMapper {
	public BookType getByID(String sonID);
	public List<BookType> getSon(String fatherID);
	public List<BookType> findall();
}
