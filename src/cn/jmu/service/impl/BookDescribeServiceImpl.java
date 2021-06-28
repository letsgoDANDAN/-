package cn.jmu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jmu.mapper.BookDescribeMapper;
import cn.jmu.po.BookDescribe;
import cn.jmu.service.BookDescribeService;

@Service
public class BookDescribeServiceImpl implements BookDescribeService {

	@Resource(name="bookDescribeMapper")
	private BookDescribeMapper bookDescribeMapper;
	
	@Override
	public int addBookDescribe(BookDescribe bookdescribe) {
		// TODO Auto-generated method stub
		return this.bookDescribeMapper.addBookDescribe(bookdescribe);
	}

	@Override
	public int deleteBookDescribe(String IBSN) {
		// TODO Auto-generated method stub
		return this.bookDescribeMapper.deleteBookDescribe(IBSN);
	}

	@Override
	public int updateBookDescribe(BookDescribe bookDescribe) {
		// TODO Auto-generated method stub
		return this.bookDescribeMapper.updateBookDescribe(bookDescribe);
	}

	@Override
	public List<BookDescribe> findByKeyword(String Keyword) {
		// TODO Auto-generated method stub
		return this.bookDescribeMapper.findByKeyword(Keyword);
	}

	@Override
	public List<BookDescribe> findAll() {
		// TODO Auto-generated method stub
		return this.bookDescribeMapper.findAll();
	}

	@Override
	public List<BookDescribe> findBySonID(String sonID) {
		// TODO Auto-generated method stub
		return this.bookDescribeMapper.findBySonID(sonID);
	}

	@Override
	public BookDescribe findByISBN(String ISBN) {
		// TODO Auto-generated method stub
		return this.bookDescribeMapper.findByISBN(ISBN);
	}

}
