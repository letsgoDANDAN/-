package cn.jmu.mapper;

import java.util.List;

import cn.jmu.po.Unit;

public interface UnitMapper {
	
	public String getUnitName(String unitID);
	
	public int addUnit(Unit unit);
    
	public List<String> getAll();
}
