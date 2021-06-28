package cn.jmu.service;

import java.util.List;

import cn.jmu.po.Unit;

public interface UnitService {
	
	public String getUnitName(String unitID);
	
	public int addUnit(Unit unit);
	
	public List<String> getAll();

}
