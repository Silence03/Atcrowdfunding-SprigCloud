package com.atguigu.atcrowdfunding.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.atcrowdfunding.common.bean.Member;
import com.atguigu.atcrowdfunding.member.service.MemberService;

@RestController
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	
	@RequestMapping("/getmember/{loginacct}")
	public Member getMemberByLoginacct(@PathVariable("loginacct")String loginacct) {
		Member m = memberService.getMemberByLoginacct(loginacct);
		return m;
	}
}
