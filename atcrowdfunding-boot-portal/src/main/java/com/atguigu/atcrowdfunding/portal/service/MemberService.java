package com.atguigu.atcrowdfunding.portal.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.atcrowdfunding.common.bean.Cert;
import com.atguigu.atcrowdfunding.common.bean.Member;
import com.atguigu.atcrowdfunding.common.bean.MemberCert;
import com.atguigu.atcrowdfunding.common.bean.Ticket;

@FeignClient("atcrowdfunding-member-service")
public interface MemberService {
	@RequestMapping("/getmember/{loginacct}")
	Member getMemberByLoginacct(@PathVariable("loginacct")String loginacct);

	@RequestMapping("/queryTicketByMemberId/{id}")
	Ticket queryTicketByMemberId(@PathVariable("id") Integer id);

	@RequestMapping("/updateAccttype")
	public int updateAccttype(@RequestBody Member loginmember);

	@RequestMapping("/updateBasicinfo")
	public int updateBasicinfo(@RequestBody Member loginmember);

	@RequestMapping("/queryCertsByAccttype/{accttype}")
	List<Cert> queryCertsByAccttype(@PathVariable("accttype")String accttype);

	@RequestMapping("/saveMemberCertList")
	void saveMemberCertList(@RequestBody List<MemberCert> mcs2);

	@RequestMapping("/updateEmail")
	int updateEmail(@RequestBody Member loginmember);

	@RequestMapping("/completeApply")
	int completeApply(@RequestBody Member loginmember);
	
}
