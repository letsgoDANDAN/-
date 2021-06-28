package cn.jmu.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jmu.mapper.UserMapper;
import cn.jmu.po.User;
import cn.jmu.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource(name="userMapper")
	private UserMapper userMapper;
	
	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		return this.userMapper.addUser(user);
	}

	@Override
	public int deleteUser(String name) {
		// TODO Auto-generated method stub
		return this.userMapper.deleteUser(name);
	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return this.userMapper.updateUser(user);
	}

	@Override
	public String login(String userid, String password) {
		// TODO Auto-generated method stub
		return this.userMapper.login(userid, password);
	}

}
