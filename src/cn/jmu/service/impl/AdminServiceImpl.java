package cn.jmu.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jmu.mapper.AdminMapper;
import cn.jmu.po.Reader;
import cn.jmu.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Resource(name="adminMapper")
	private AdminMapper adminMapper;
	@Override
	public int addAdmin(Reader reader) {
		// TODO Auto-generated method stub
		return this.adminMapper.addAdmin(reader);
	}

	@Override
	public int deleteAdmin(String name) {
		// TODO Auto-generated method stub
		return this.adminMapper.deleteAdmin(name);
	}

	@Override
	public int updateAdmin(String name) {
		// TODO Auto-generated method stub
		return this.adminMapper.updateAdmin(name);
	}

}
