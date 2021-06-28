package cn.jmu.mapper;

import java.util.List;

import cn.jmu.po.Press;

public interface PressMapper {
	
	public String getPressName(String publicID);
	
	public int addPress(Press press);
	
	public List<Press> findall();

}
