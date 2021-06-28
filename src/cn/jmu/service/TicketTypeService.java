package cn.jmu.service;

import java.util.List;

import cn.jmu.po.TicketType;

public interface TicketTypeService {
	
	public String getTicketTypeName(String finetypeID);
	public List<TicketType> findall();

}
