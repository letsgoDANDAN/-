package cn.jmu.service;

import cn.jmu.po.BorrowConditions;

public interface BorrowConditionsService {
	
	public int selectAll();
	public BorrowConditions selectByType(String readertypeID,String borrowtypeID);
	public int updateBorrowConditions(BorrowConditions borrowconditions);

}
