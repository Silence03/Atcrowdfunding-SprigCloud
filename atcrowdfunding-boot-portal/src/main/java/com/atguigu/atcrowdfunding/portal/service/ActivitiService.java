package com.atguigu.atcrowdfunding.portal.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("atcrowdfunding-activiti-service")
public interface ActivitiService {

	@RequestMapping("/act/startprocess/{loginacct}")
	public String startProcess(@PathVariable("loginacct")String loginacct);

}
