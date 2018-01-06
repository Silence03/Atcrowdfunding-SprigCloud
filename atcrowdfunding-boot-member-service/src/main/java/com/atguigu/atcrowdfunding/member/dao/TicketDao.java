package com.atguigu.atcrowdfunding.member.dao;

import org.apache.ibatis.annotations.Insert;

import com.atguigu.atcrowdfunding.common.bean.Ticket;

public interface TicketDao {
	@Insert("insert into t_ticket(memberid,piid,status,pstep) values(#{memberid},#{piid},#{status},#{pstep})")
	int saveticket(Ticket ticket);

}
