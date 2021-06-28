package cn.jmu.mapper;

import java.util.List;

import cn.jmu.po.TicketType;

public interface TicketTypeMapper {
	
	public String getTicketTypeName(String finetypeID);
	
	public List<TicketType> findall();

}
