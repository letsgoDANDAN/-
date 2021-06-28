package cn.jmu.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jmu.mapper.TipMapper;
import cn.jmu.po.Tip;
import cn.jmu.service.TipService;

@Service
public class TipServiceImpl implements TipService {

	@Resource(name="tipMapper")
	private TipMapper tipMapper;
	@Override
	public int getBookNumber(String ISBN, String branchID) {
		// TODO Auto-generated method stub
		return this.tipMapper.getBookNumber(ISBN, branchID);
	}

	@Override
	public int addTip(Tip tip) {
		// TODO Auto-generated method stub
		return this.tipMapper.addTip(tip);
	}

	@Override
	public int updateTip(Tip tip) {
		// TODO Auto-generated method stub
		return this.tipMapper.updateTip(tip);
	}

}
