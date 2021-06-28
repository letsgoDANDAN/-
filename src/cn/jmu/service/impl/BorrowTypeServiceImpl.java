package cn.jmu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jmu.mapper.BorrowTypeMapper;
import cn.jmu.po.BorrowType;
import cn.jmu.service.BorrowTypeService;

@Service
public class BorrowTypeServiceImpl implements BorrowTypeService {

	@Resource(name="borrowTypeMapper")
	private BorrowTypeMapper borrowTypeMapper;
	
	@Override
	public String getBorrowTypeName(String borrowtypename) {
		// TODO Auto-generated method stub
		return this.borrowTypeMapper.getBorrowTypeName(borrowtypename);
	}

	@Override
	public List<BorrowType> findall(){
		return this.borrowTypeMapper.findall();
	}
}
