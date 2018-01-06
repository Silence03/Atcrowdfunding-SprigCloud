package com.atguigu.atcrowdfunding.portal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.common.bean.BaseController;
import com.atguigu.atcrowdfunding.common.bean.Member;
import com.atguigu.atcrowdfunding.common.bean.Ticket;
import com.atguigu.atcrowdfunding.common.util.Const;
import com.atguigu.atcrowdfunding.portal.service.ActivitiService;
import com.atguigu.atcrowdfunding.portal.service.MemberService;
import com.atguigu.atcrowdfunding.portal.service.TicketService;



@Controller
public class MemberController extends BaseController{
	@Autowired
	private MemberService memberService;
	@Autowired
	ActivitiService activitiService;
	@Autowired
	TicketService ticketService;
	
	/**
	 * 点击实名认证
	 */
	@RequestMapping("/member/apply")
	public String member(HttpSession session) {
		// 根据登录会员id,查询流程单
		Member member = (Member) session.getAttribute(Const.LOGINMEMBER);
		Ticket ticket = memberService.queryTicketByMemberId(member.getId());
		// ○ 如果流程单为null
		if(ticket==null) {
			// § 启动流程
			String piid = activitiService.startProcess(member.getLoginacct());
			// § 保存流程单
			ticket = new Ticket();
			ticket.setMemberid(member.getId());
			ticket.setStatus("0");
			ticket.setPstep("accttype");
			ticket.setPiid(piid);
			
			int i = ticketService.saveticket(ticket);
			// § 跳转到账户类型选择页面
			return "member/accttype";
		}else {
			// ○ 如果流程单不为null
			//	§ 根据流程单"pstep"判断,跳转到相关页面.
			if("accttype".equals(ticket.getPstep())) {
				return "member/accttype";
			}else if("basicinfo".equals(ticket.getPstep())) {
				return "member/basicinfo";
			}else if("certupload".equals(ticket.getPstep())) {
				return "member/certupload";
			}else if("checkemail".equals(ticket.getPstep())) {
				return "member/checkemail";
			}else if("checkauthcode".equals(ticket.getPstep())) {
				return "member/checkauthcode";
			}
		}
		return "accttype";
	}
	/**
	 * 用户类型更新
	 */
	@ResponseBody
	@RequestMapping("/member/updateAccttype")
	public Object updateAccttype(String accttype,HttpSession session) {
		start();
		try {
			Member loginmember = (Member) session.getAttribute(Const.LOGINMEMBER);
			loginmember.setAccttype(accttype);
			session.setAttribute(Const.LOGINMEMBER, loginmember);
			int i = memberService.updateAccttype(loginmember);
			if(i==1) {
				success(true);
			}else {
				success(false);
			}
		} catch (Exception e) {
			success(false);
			e.printStackTrace();
		}
		return end();
	}
	
	/**
	 * 用户基本信息更新
	 */
	@ResponseBody
	@RequestMapping("/member/updateBasicinfo")
	public Object updateBasicinfo(Member member,HttpSession session) {
		start();
		try {
			Member loginmember = (Member) session.getAttribute(Const.LOGINMEMBER);
			loginmember.setRealname(member.getRealname());
			loginmember.setCardnum(member.getCardnum());
			loginmember.setTel(member.getTel());
			session.setAttribute(Const.LOGINMEMBER, loginmember);
			int i = memberService.updateBasicinfo(loginmember);
			if(i==1) {
				success(true);
			}else {
				success(false);
			}
		} catch (Exception e) {
			success(false);
			e.printStackTrace();
		}
		return end();
	}
	
	
	
	
	
	
	
	
}
