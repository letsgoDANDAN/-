package cn.jmu.mapper;

import org.apache.ibatis.annotations.Param;

import cn.jmu.po.User;

public interface UserMapper {
	
	public int addUser(User user);
	public int deleteUser(String name);
	public int updateUser(User user);
	public String login(@Param("userid")String userid,@Param("password")String password);
}
