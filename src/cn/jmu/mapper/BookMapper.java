package cn.jmu.mapper;

import java.util.List;

import cn.jmu.po.Book;

public interface BookMapper {
	public int addBook(Book book);
	public int deleteBook(String bookID);
	public int updateBook(Book book);
	public Book findByBookID(String bookID);
	public List<Book> findByIBSN(String ISBN);
	public List<Book> findFreeBook(String ISBN);
	public int deleteBookByISBN(String ISBN);
	public List<Book> findByStatus(int readerstatus);
}
