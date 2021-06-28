package cn.jmu.mapper;

import cn.jmu.po.Reader;

public interface AdminMapper {
	
	public int addAdmin(Reader reader);
	public int deleteAdmin(String name);
	public int updateAdmin(String name);

}
