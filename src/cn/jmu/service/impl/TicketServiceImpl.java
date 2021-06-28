package cn.jmu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jmu.mapper.TicketMapper;
import cn.jmu.po.Ticket;
import cn.jmu.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Resource(name="ticketMapper")
	private TicketMapper ticketMapper;
	
	@Override
	public int addTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		return this.ticketMapper.addTicket(ticket);
	}

	@Override
	public int deleteTicket(String fineid) {
		// TODO Auto-generated method stub
		return this.ticketMapper.deleteTicket(fineid);
	}

	@Override
	public int updateTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		return this.ticketMapper.updateTicket(ticket);
	}

	@Override
	public List<Ticket> findByUseid(String userid) {
		// TODO Auto-generated method stub
		return this.ticketMapper.findByUseid(userid);
	}

	@Override
	public List<Ticket> findAll() {
		// TODO Auto-generated method stub
		return this.ticketMapper.findAll();
	}

	@Override
	public Ticket findById(String fineid) {
		// TODO Auto-generated method stub
		return this.ticketMapper.findById(fineid);
	}

}
