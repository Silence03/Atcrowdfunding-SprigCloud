package com.atguigu.atcrowdfunding.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.atcrowdfunding.common.bean.Ticket;
import com.atguigu.atcrowdfunding.member.dao.TicketDao;
import com.atguigu.atcrowdfunding.member.service.TicketService;
@Service
@Transactional
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	TicketDao ticketDao;

	@Override
	public int saveticket(Ticket ticket) {
		// TODO Auto-generated method stub
		return ticketDao.saveticket(ticket);
	}

}
