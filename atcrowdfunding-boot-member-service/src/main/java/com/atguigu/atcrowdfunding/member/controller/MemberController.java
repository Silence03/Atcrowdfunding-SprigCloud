package com.atguigu.atcrowdfunding.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.atcrowdfunding.common.bean.Member;
import com.atguigu.atcrowdfunding.common.bean.Ticket;
import com.atguigu.atcrowdfunding.member.service.ActivitiService;
import com.atguigu.atcrowdfunding.member.service.MemberService;
import com.atguigu.atcrowdfunding.member.service.TicketService;

@RestController
public class MemberController {
	
	@Autowired
	MemberService memberService;
	@Autowired
	TicketService ticketService;
	@Autowired
	ActivitiService activitiService;
	
	
	@RequestMapping("/getmember/{loginacct}")
	public Member getMemberByLoginacct(@PathVariable("loginacct")String loginacct) {
		Member m = memberService.getMemberByLoginacct(loginacct);
		return m;
	}
	
	@RequestMapping("/queryTicketByMemberId/{id}")
	Ticket queryTicketByMemberId(@PathVariable("id") Integer id) {
		return memberService.queryTicketByMemberId(id);
	}
	
	@RequestMapping("/updateAccttype")
	public int updateAccttype(@RequestBody Member loginmember) {
		// 更新会员账户类型
		int i = memberService.updateAccttype(loginmember);
		// 更新流程审批单的流程步骤
		Ticket ticket = memberService.queryTicketByMemberId(loginmember.getId());
		ticket.setPstep("basicinfo");
		int j = memberService.updateticket(ticket);
		// 让流程继续执行:完成任务taskService.complete(taskId)
		Map<String,Object> map = new HashMap<>();
		map.put("loginacct", loginmember.getLoginacct());
		map.put("piid", ticket.getPiid());
		activitiService.completetask(map);
		
		return i;
	}
	
	@RequestMapping("/updateBasicinfo")
	public int updateBasicinfo(@RequestBody Member loginmember) {
		// • 更新会员信息
		int i = memberService.updateBasicinfo(loginmember);
		// • 更新流程单步骤"pstep"="certupload"
		Ticket ticket = memberService.queryTicketByMemberId(loginmember.getId());
		ticket.setPstep("certupload");
		int j = memberService.updateticket(ticket);
		// • 完成当前任务节点
		// ○ 设置流程变量${ flag == true}
		Map<String,Object> map = new HashMap<>();
		map.put("loginacct", loginmember.getLoginacct());
		map.put("piid", ticket.getPiid());
		map.put("flag", true);
		activitiService.completetask(map);

		return i;
	}
}
