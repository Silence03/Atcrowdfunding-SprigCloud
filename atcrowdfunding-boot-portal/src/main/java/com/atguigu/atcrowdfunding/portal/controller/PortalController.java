package com.atguigu.atcrowdfunding.portal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.common.bean.BaseController;
import com.atguigu.atcrowdfunding.common.bean.Member;
import com.atguigu.atcrowdfunding.common.util.Const;
import com.atguigu.atcrowdfunding.common.util.MD5Util;
import com.atguigu.atcrowdfunding.portal.service.MemberService;

@Controller
public class PortalController extends BaseController{
	@Autowired
	MemberService memberService;
	
	/**
	 * 跳转登陆页面
	 */
	@RequestMapping("/login")
	public String tologin() {
		return "login";
	}
	/**
	 * 登陆成功跳转页面
	 */
	@RequestMapping("/member")
	public String member() {
		return "member";
	}
	/**
	 * 注销登陆
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(Const.LOGINMEMBER);
		return "index";
	}
	/**
	 * 执行登陆操作
	 */
	@ResponseBody
	@RequestMapping("/dologin")
	public Object dologin(Member member,HttpSession session) {
		System.out.println(member);
		start();
		try {
			Member m = memberService.getMemberByLoginacct(member.getLoginacct());
			System.out.println(m);
			if(m==null) {
				success(false);
				message("该账号不存在，请注册后再登录！");
			}else {
				if(m.getUserpswd().equals(MD5Util.digest(member.getUserpswd()))) {
					session.setAttribute(Const.LOGINMEMBER, m);
					System.out.println("m : "+m);
					success(true);
				}else {
					success(false);
					message("密码错误请重新登录！");
				}
			}
			success(true);
		} catch (Exception e) {
			success(false);
			message("该账号不存在，请注册后再登录！");
			e.printStackTrace();
		}
		return end();
	}
	
}
