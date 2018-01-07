package com.atguigu.atcrowdfunding.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.atguigu.atcrowdfunding.common.bean.Cert;
import com.atguigu.atcrowdfunding.common.bean.Member;
import com.atguigu.atcrowdfunding.common.bean.MemberCert;
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

	List<Cert> queryCertsByAccttype(String accttype);

	void saveMemberCertList(List<MemberCert> mcs2);

	@Select("select * from t_member where id=#{memberid}")
	Member queryMemberById(Integer memberid);

	@Update("update t_member set email=#{email} where id=#{id}")
	int updateEmail(Member loginmember);

	@Update("update t_ticket set pstep=#{pstep},authcode=#{authcode} where memberid=#{memberid}")
	int updateticketIdAndAuthcode(Ticket ticket);

	@Update("update t_member set authstatus=#{authstatus} where id=#{id}")
	int updateStatus(Member loginmember);

	@Update("update t_ticket set pstep=#{pstep},status=#{status} where memberid=#{memberid}")
	int updateticketcomplete(Ticket ticket);

}
