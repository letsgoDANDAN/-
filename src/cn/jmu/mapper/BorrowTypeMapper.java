package cn.jmu.mapper;

import java.util.List;

import cn.jmu.po.BorrowType;

public interface BorrowTypeMapper {
	
	public String getBorrowTypeName(String borrowtypeID);

	public List<BorrowType> findall();
}
