package com.atguigu.atcrowdfunding.portal.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.atcrowdfunding.common.bean.Member;

@FeignClient("atcrowdfunding-member-service")
public interface MemberService {
	@RequestMapping("/getmember/{loginacct}")
	Member getMemberByLoginacct(@PathVariable("loginacct")String loginacct);
	
}
