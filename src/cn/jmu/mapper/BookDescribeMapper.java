package cn.jmu.mapper;

import java.util.List;

import cn.jmu.po.BookDescribe;

public interface BookDescribeMapper {
	public int addBookDescribe(BookDescribe bookdescribe);
	public int deleteBookDescribe(String IBSN);
	public int updateBookDescribe(BookDescribe bookDescribe);
	public List<BookDescribe> findByKeyword(String Keyword);
	public List<BookDescribe> findAll();
	public List<BookDescribe> findBySonID(String sonID);
	public BookDescribe findByISBN(String ISBN);
}
