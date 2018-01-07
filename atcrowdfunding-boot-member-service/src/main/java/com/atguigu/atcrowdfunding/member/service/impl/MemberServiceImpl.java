package com.atguigu.atcrowdfunding.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.atcrowdfunding.common.bean.Cert;
import com.atguigu.atcrowdfunding.common.bean.Member;
import com.atguigu.atcrowdfunding.common.bean.MemberCert;
import com.atguigu.atcrowdfunding.common.bean.Ticket;
import com.atguigu.atcrowdfunding.member.dao.MemberDao;
import com.atguigu.atcrowdfunding.member.service.MemberService;

@Transactional //开启事务
@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDao memberDao;

	@Override
	public Member getMemberByLoginacct(String loginacct) {
		Member m = memberDao.getMemberByLoginacct(loginacct);
		return m;
	}

	@Override
	public Ticket queryTicketByMemberId(Integer id) {
		
		return memberDao.queryTicketByMemberId(id);
	}

	@Override
	public int updateAccttype(Member loginmember) {
		// TODO Auto-generated method stub
		return memberDao.updateAccttype(loginmember);
	}

	@Override
	public int updateticket(Ticket ticket) {
		// TODO Auto-generated method stub
		return memberDao.updateticket(ticket);
	}

	@Override
	public int updateBasicinfo(Member loginmember) {
		// TODO Auto-generated method stub
		return memberDao.updateBasicinfo(loginmember);
	}

	@Override
	public List<Cert> queryCertsByAccttype(String accttype) {
		// TODO Auto-generated method stub
		return memberDao.queryCertsByAccttype(accttype);
	}

	@Override
	public void saveMemberCertList(List<MemberCert> mcs2) {
		memberDao.saveMemberCertList(mcs2);
		
	}

	@Override
	public Member queryMemberById(Integer memberid) {
		// TODO Auto-generated method stub
		return memberDao.queryMemberById(memberid);
	}

	@Override
	public int updateEmail(Member loginmember) {
		// TODO Auto-generated method stub
		return memberDao.updateEmail(loginmember);
	}

	@Override
	public int updateticketIdAndAuthcode(Ticket ticket) {
		// TODO Auto-generated method stub
		return memberDao.updateticketIdAndAuthcode(ticket);
	}

	@Override
	public int updateStatus(Member loginmember) {
		// TODO Auto-generated method stub
		return memberDao.updateStatus(loginmember);
	}

	@Override
	public int updateticketcomplete(Ticket ticket) {
		// TODO Auto-generated method stub
		return memberDao.updateticketcomplete(ticket);
	}
	
}
