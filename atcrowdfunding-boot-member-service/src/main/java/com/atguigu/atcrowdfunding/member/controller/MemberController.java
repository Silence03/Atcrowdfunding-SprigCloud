package com.atguigu.atcrowdfunding.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.atcrowdfunding.common.bean.Cert;
import com.atguigu.atcrowdfunding.common.bean.Member;
import com.atguigu.atcrowdfunding.common.bean.MemberCert;
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
	
	@RequestMapping("/queryCertsByAccttype/{accttype}")
	List<Cert> queryCertsByAccttype(@PathVariable("accttype")String accttype){
		return memberService.queryCertsByAccttype(accttype);
	}
	
	@RequestMapping("/saveMemberCertList")
	void saveMemberCertList(@RequestBody List<MemberCert> mcs2) {
		memberService.saveMemberCertList(mcs2);
		MemberCert memberCert = mcs2.get(0);
		Integer memberid = memberCert.getMemberid();
		Member member = memberService.queryMemberById(memberid);
		// • 更新流程单步骤"pstep"="certupload"
		Ticket ticket = memberService.queryTicketByMemberId(member.getId());
		ticket.setPstep("checkemail");
		int j = memberService.updateticket(ticket);
		// • 完成当前任务节点
		// ○ 设置流程变量${ flag == true}
		Map<String,Object> map = new HashMap<>();
		map.put("loginacct", member.getLoginacct());
		map.put("piid", ticket.getPiid());
		map.put("flag", true);
		activitiService.completetask(map);
	}
	
	@RequestMapping("/updateEmail")
	int updateEmail(@RequestBody Member loginmember) {
		//更新用户邮箱
		int i = memberService.updateEmail(loginmember);
		// • 更新流程单步骤"pstep"="certupload"
		Ticket ticket = memberService.queryTicketByMemberId(loginmember.getId());
		ticket.setPstep("checkauthcode");
			//获取验证码
		StringBuffer sb=new StringBuffer();
		for (int k = 0; k < 4; k++) {
			int nextInt = new Random().nextInt(10);
			sb.append(nextInt);
		}
		String authcode=sb.toString();
		System.out.println("========================================");
		System.out.println("=================>"+authcode+"<=================");
		System.out.println("=================^^^^^^=================");
		ticket.setAuthcode(authcode);
		int j = memberService.updateticketIdAndAuthcode(ticket);
		// • 完成当前任务节点
		// ○ 设置流程变量${ flag == true}
		Map<String,Object> map = new HashMap<>();
		map.put("loginacct", loginmember.getLoginacct());
		map.put("piid", ticket.getPiid());
		map.put("flag", true);
		map.put("email", loginmember.getEmail());
		map.put("authcode", authcode);
		activitiService.completetask(map);
		return i;
	}
	
	@RequestMapping("/completeApply")
	int completeApply(@RequestBody Member loginmember) {
		//1.更新用户状态: 0 -> 1
		int i = memberService.updateStatus(loginmember);
		//2.更新流程单: status  0->1   , pstep="ok"
		Ticket ticket = memberService.queryTicketByMemberId(loginmember.getId());
		ticket.setPstep("ok");
		ticket.setStatus("1");
		int j = memberService.updateticketcomplete(ticket);
		//3.完成当前步骤
		Map<String,Object> map = new HashMap<>();
		map.put("loginacct", loginmember.getLoginacct());
		map.put("piid", ticket.getPiid());
		map.put("flag", true);
		activitiService.completetask(map);
		return i;
	}
}
