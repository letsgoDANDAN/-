package cn.jmu.service;

import java.util.List;

import cn.jmu.po.Ticket;

public interface TicketService {
	
	public int addTicket(Ticket ticket);
	public int deleteTicket(String fineid);
	public int updateTicket(Ticket ticket);
	public List<Ticket> findByUseid(String userid);
	public List<Ticket> findAll();
	public Ticket findById(String fineid);

}
