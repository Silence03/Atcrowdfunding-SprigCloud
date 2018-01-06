package com.atguigu.atcrowdfunding.member.service;

import com.atguigu.atcrowdfunding.common.bean.Member;
import com.atguigu.atcrowdfunding.common.bean.Ticket;

public interface MemberService {

	Member getMemberByLoginacct(String loginacct);

	Ticket queryTicketByMemberId(Integer id);

	int updateAccttype(Member loginmember);

	int updateticket(Ticket ticket);

	int updateBasicinfo(Member loginmember);

}
