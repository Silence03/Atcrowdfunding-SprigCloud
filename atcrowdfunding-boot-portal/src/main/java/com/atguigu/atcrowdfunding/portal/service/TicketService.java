package com.atguigu.atcrowdfunding.portal.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.atcrowdfunding.common.bean.Ticket;


@FeignClient("atcrowdfunding-member-service")
public interface TicketService {

	@RequestMapping("/saveticket")
	public int saveticket(@RequestBody Ticket ticket);

}
