package cn.jmu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jmu.mapper.AuthorMapper;
import cn.jmu.po.Author;
import cn.jmu.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

	
	@Resource(name="authorMapper")
	private AuthorMapper authorMapper;
	
	@Override
	public String getAuthorName(String authorID) {
		// TODO Auto-generated method stub
		return this.authorMapper.getAuthorName(authorID);
	}

	@Override
	public int addAuthor(Author author) {
		// TODO Auto-generated method stub
		return this.authorMapper.addAuthor(author);
	}

	@Override
	public List<Author> findall() {
		// TODO Auto-generated method stub
		return this.authorMapper.findall();
	}

}
