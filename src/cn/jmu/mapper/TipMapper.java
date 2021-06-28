package cn.jmu.mapper;

import org.apache.ibatis.annotations.Param;

import cn.jmu.po.Tip;

public interface TipMapper {
	
	public int getBookNumber(@Param("ISBN")String ISBN,@Param("branchID")String branchID);
	public int addTip(Tip tip);
	public int updateTip(Tip tip);

}
