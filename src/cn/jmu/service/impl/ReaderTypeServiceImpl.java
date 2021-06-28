package cn.jmu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jmu.mapper.ReaderTypeMapper;
import cn.jmu.service.ReaderTypeService;

@Service
public class ReaderTypeServiceImpl implements ReaderTypeService {

	@Resource(name="readerTypeMapper")
	private ReaderTypeMapper readerTypeMapper;
	
	@Override
	public String getReaderTypeName(String readertypeID) {
		// TODO Auto-generated method stub
		return this.readerTypeMapper.getReaderTypeName(readertypeID);
	}

	@Override
	public List<String> getAll() {
		// TODO Auto-generated method stub
		return this.readerTypeMapper.getAll();
	}

}
