package com.atguigu.atcrowdfunding.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.atcrowdfunding.common.bean.Member;
import com.atguigu.atcrowdfunding.member.dao.MemberDao;
import com.atguigu.atcrowdfunding.member.service.MemberService;

@Transactional(readOnly=true) //开启事务
@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDao memberDao;

	@Override
	public Member getMemberByLoginacct(String loginacct) {
		Member m = memberDao.getMemberByLoginacct(loginacct);
		return m;
	}
	
}
