package cn.jmu.service;

import java.util.List;

import cn.jmu.po.BookType;

public interface BookTypeService {
	public BookType getByID(String sonID);
	public List<BookType> getSon(String fatherID);
	public List<BookType> findall();
}
