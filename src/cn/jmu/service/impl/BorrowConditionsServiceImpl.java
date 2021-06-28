package cn.jmu.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jmu.mapper.BorrowConditionsMapper;
import cn.jmu.po.BorrowConditions;
import cn.jmu.service.BorrowConditionsService;

@Service
public class BorrowConditionsServiceImpl implements BorrowConditionsService {

	@Resource(name="borrowConditionsMapper")
	private BorrowConditionsMapper borrowConditionsMapper;
	
	@Override
	public int selectAll() {
		// TODO Auto-generated method stub
		return this.borrowConditionsMapper.selectAll();
	}

	@Override
	public BorrowConditions selectByType(String readertypeID, String borrowtypeID) {
		// TODO Auto-generated method stub
		return this.borrowConditionsMapper.selectByType(readertypeID, borrowtypeID);
	}

	@Override
	public int updateBorrowConditions(BorrowConditions borrowconditions) {
		// TODO Auto-generated method stub
		return this.borrowConditionsMapper.updateBorrowConditions(borrowconditions);
	}

}
