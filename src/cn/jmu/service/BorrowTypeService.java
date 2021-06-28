package cn.jmu.service;

import java.util.List;

import cn.jmu.po.BorrowType;

public interface BorrowTypeService {
	
	public String getBorrowTypeName(String borrowtypename);

	public List<BorrowType> findall();
}
