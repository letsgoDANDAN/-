package cn.jmu.service;

import java.util.List;

import cn.jmu.po.Press;

public interface PressService {
	
	public String getPressName(String publicID);
	
	public int addPress(Press press);

	public List<Press> findall();
}
