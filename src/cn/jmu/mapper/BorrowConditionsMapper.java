package cn.jmu.mapper;

import org.apache.ibatis.annotations.Param;

import cn.jmu.po.BorrowConditions;

public interface BorrowConditionsMapper {
	
	public int selectAll();
	public BorrowConditions selectByType(@Param("readertypeID")String readertypeID,@Param("borrowtypeID")String borrowtypeID);
	public int updateBorrowConditions(BorrowConditions borrowconditions);

}
