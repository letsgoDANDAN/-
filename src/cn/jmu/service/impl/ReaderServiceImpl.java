package cn.jmu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jmu.mapper.ReaderMapper;
import cn.jmu.po.Reader;
import cn.jmu.service.ReaderService;

@Service
public class ReaderServiceImpl implements ReaderService {

	@Resource(name="readerMapper")
	private ReaderMapper readerMapper;
	
	@Override
	public int addReader(Reader reader) {
		// TODO Auto-generated method stub
		return this.readerMapper.addReader(reader);
	}

	@Override
	public int deleteReader(String name) {
		// TODO Auto-generated method stub
		return this.readerMapper.deleteReader(name);
	}

	@Override
	public int updateReader(Reader reader) {
		// TODO Auto-generated method stub
		return this.readerMapper.updateReader(reader);
	}

	@Override
	public Reader findByName(String name) {
		// TODO Auto-generated method stub
		return this.readerMapper.findByName(name);
	}

	@Override
	public List<Reader> findAll() {
		// TODO Auto-generated method stub
		return this.readerMapper.findAll();
	}

	@Override
	public int checkReader(String name) {
		// TODO Auto-generated method stub
		return this.readerMapper.checkReader(name);
	}

	@Override
	public Reader findByUserID(String userID) {
		// TODO Auto-generated method stub
		return this.readerMapper.findByUserID(userID);
	}

}
