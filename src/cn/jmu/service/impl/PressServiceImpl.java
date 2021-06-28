package cn.jmu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jmu.mapper.PressMapper;
import cn.jmu.po.Press;
import cn.jmu.service.PressService;

@Service
public class PressServiceImpl implements PressService {

	@Resource(name="pressMapper")
	private PressMapper pressMapper;
	
	@Override
	public String getPressName(String publicID) {
		// TODO Auto-generated method stub
		return this.pressMapper.getPressName(publicID);
	}

	@Override
	public int addPress(Press press) {
		// TODO Auto-generated method stub
		return this.pressMapper.addPress(press);
	}

	@Override
	public List<Press> findall() {
		// TODO Auto-generated method stub
		return this.pressMapper.findall();
	}

}
