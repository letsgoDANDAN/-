package cn.jmu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jmu.mapper.BookTypeMapper;
import cn.jmu.po.BookType;
import cn.jmu.service.BookTypeService;

@Service
public class BookTypeServiceImpl implements BookTypeService {

	@Resource(name="bookTypeMapper")
	private BookTypeMapper bookTypeMapper;
	
	@Override
	public List<BookType> getSon(String fatherID) {
		// TODO Auto-generated method stub
		return this.bookTypeMapper.getSon(fatherID);
	}

	@Override
	public BookType getByID(String sonID) {
		// TODO Auto-generated method stub
		return this.bookTypeMapper.getByID(sonID);
	}

	@Override
	public List<BookType> findall() {
		// TODO Auto-generated method stub
		return this.bookTypeMapper.findall();
	}

}
