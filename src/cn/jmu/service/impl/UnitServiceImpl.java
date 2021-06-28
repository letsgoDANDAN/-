package cn.jmu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jmu.mapper.UnitMapper;
import cn.jmu.po.Unit;
import cn.jmu.service.UnitService;

@Service
public class UnitServiceImpl implements UnitService {

	@Resource(name="unitMapper")
	private UnitMapper unitMapper;
	@Override
	public String getUnitName(String unitID) {
		// TODO Auto-generated method stub
		return this.unitMapper.getUnitName(unitID);
	}

	@Override
	public int addUnit(Unit unit) {
		// TODO Auto-generated method stub
		return this.unitMapper.addUnit(unit);
	}

	public List<String> getAll() {
		// TODO Auto-generated method stub
		return this.unitMapper.getAll();
	}

}
