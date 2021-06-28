package cn.jmu.service;

import cn.jmu.po.Reader;

public interface AdminService {
	
	public int addAdmin(Reader reader);
	public int deleteAdmin(String name);
	public int updateAdmin(String name);

}
