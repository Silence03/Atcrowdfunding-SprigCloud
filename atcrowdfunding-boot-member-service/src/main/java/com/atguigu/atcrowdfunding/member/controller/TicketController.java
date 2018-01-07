package com.atguigu.atcrowdfunding.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.atcrowdfunding.member.service.TicketService;

import com.atguigu.atcrowdfunding.common.bean.Ticket;
@RestController
public class TicketController {
	@Autowired
	TicketService ticketService;

	@RequestMapping("/saveticket")
	public int saveticket(@RequestBody Ticket ticket) {
		return ticketService.saveticket(ticket);
	}
}
