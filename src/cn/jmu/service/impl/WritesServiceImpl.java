package cn.jmu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jmu.mapper.WritesMapper;

import cn.jmu.po.Writes;

@Service
public class WritesServiceImpl implements cn.jmu.service.WritesService {

	@Resource(name="writesMapper")
	private WritesMapper writesMapper;
	
	@Override
	public List<String> getAuthorID(String ISBN) {
		// TODO Auto-generated method stub
		return this.writesMapper.getAuthorID(ISBN);
	}

	@Override
	public int addWrite(Writes writes) {
		// TODO Auto-generated method stub
		return this.writesMapper.addWrite(writes);
	}

	@Override
	public int deleteWrite(String ISBN) {
		// TODO Auto-generated method stub
		return this.writesMapper.deleteWrite(ISBN);
	}

}
