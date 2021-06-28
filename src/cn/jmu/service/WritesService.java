package cn.jmu.service;

import java.util.List;

import cn.jmu.po.Writes;

public interface WritesService {
	
	public List<String> getAuthorID(String ISBN);
	
	public int addWrite(Writes writes);

	public int deleteWrite(String ISBN);
}
