package cn.jmu.mapper;

import java.util.List;

import cn.jmu.po.Writes;

public interface WritesMapper {
	
	public List<String> getAuthorID(String ISBN);
	
	public int addWrite(Writes writes);

	public int deleteWrite(String ISBN);
}
