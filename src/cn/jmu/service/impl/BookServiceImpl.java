package cn.jmu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jmu.mapper.BookMapper;
import cn.jmu.po.Book;
import cn.jmu.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Resource(name="bookMapper")
	private BookMapper bookMapper;
	
	@Override
	public int addBook(Book book) {
		// TODO Auto-generated method stub
		return this.bookMapper.addBook(book);
	}

	@Override
	public int deleteBook(String bookID) {
		// TODO Auto-generated method stub
		return this.bookMapper.deleteBook(bookID);
	}

	@Override
	public int updateBook(Book book) {
		// TODO Auto-generated method stub
		return this.bookMapper.updateBook(book);
	}

	@Override
	public Book findByBookID(String bookID) {
		// TODO Auto-generated method stub
		return this.bookMapper.findByBookID(bookID);
	}

	@Override
	public List<Book> findByISBN(String ISBN) {
		// TODO Auto-generated method stub
		return this.bookMapper.findByIBSN(ISBN);
	}

	@Override
	public List<Book> findFreeBook(String ISBN) {
		// TODO Auto-generated method stub
		return this.bookMapper.findFreeBook(ISBN);
	}

	@Override
	public int deleteBookByISBN(String ISBN) {
		// TODO Auto-generated method stub
		return this.bookMapper.deleteBookByISBN(ISBN);
	}

	@Override
	public List<Book> findByStatus(int readerstatus) {
		// TODO Auto-generated method stub
		return this.bookMapper.findByStatus(readerstatus);
	}

}
