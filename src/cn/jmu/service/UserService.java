package cn.jmu.service;

import cn.jmu.po.User;

public interface UserService {
	
	public int addUser(User user);
	public int deleteUser(String name);
	public int updateUser(User user);
	public String login(String userid,String password);
}
