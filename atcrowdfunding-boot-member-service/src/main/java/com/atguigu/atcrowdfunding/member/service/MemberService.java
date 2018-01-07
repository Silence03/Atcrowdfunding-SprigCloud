package com.atguigu.atcrowdfunding.member.service;

import java.util.List;

import com.atguigu.atcrowdfunding.common.bean.Cert;
import com.atguigu.atcrowdfunding.common.bean.Member;
import com.atguigu.atcrowdfunding.common.bean.MemberCert;
import com.atguigu.atcrowdfunding.common.bean.Ticket;

public interface MemberService {

	Member getMemberByLoginacct(String loginacct);

	Ticket queryTicketByMemberId(Integer id);

	int updateAccttype(Member loginmember);

	int updateticket(Ticket ticket);

	int updateBasicinfo(Member loginmember);

	List<Cert> queryCertsByAccttype(String accttype);

	void saveMemberCertList(List<MemberCert> mcs2);

	Member queryMemberById(Integer memberid);

	int updateEmail(Member loginmember);

	int updateticketIdAndAuthcode(Ticket ticket);

	int updateStatus(Member loginmember);

	int updateticketcomplete(Ticket ticket);

}
