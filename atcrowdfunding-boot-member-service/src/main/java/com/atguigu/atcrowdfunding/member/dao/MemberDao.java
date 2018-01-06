package com.atguigu.atcrowdfunding.member.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.atguigu.atcrowdfunding.common.bean.Member;
import com.atguigu.atcrowdfunding.common.bean.Ticket;


public interface MemberDao {
	@Select("select * from t_member where loginacct=#{loginacct}")
	Member getMemberByLoginacct(String loginacct);

	@Select("select * from t_ticket where memberid=#{id} and status='0'")
	Ticket queryTicketByMemberId(Integer id);

	@Update("update t_member set accttype=#{accttype} where id=#{id}")
	int updateAccttype(Member loginmember);

	@Update("update t_ticket set pstep=#{pstep} where memberid=#{memberid}")
	int updateticket(Ticket ticket);

	@Update("update t_member set realname=#{realname},cardnum=#{cardnum},tel=#{tel} where id=#{id}")
	int updateBasicinfo(Member loginmember);

}
