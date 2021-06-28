package cn.jmu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jmu.mapper.TicketTypeMapper;
import cn.jmu.po.TicketType;
import cn.jmu.service.TicketTypeService;

@Service
public class TicketTypeServiceImpl implements TicketTypeService {

	@Resource(name="ticketTypeMapper")
	private TicketTypeMapper ticketTypeMapper;
	@Override
	public String getTicketTypeName(String finetypeID) {
		// TODO Auto-generated method stub
		return this.ticketTypeMapper.getTicketTypeName(finetypeID);
	}
	@Override
	public List<TicketType> findall() {
		// TODO Auto-generated method stub
		return this.ticketTypeMapper.findall();
	}

}
