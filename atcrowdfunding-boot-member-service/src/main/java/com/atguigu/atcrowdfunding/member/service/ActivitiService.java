package com.atguigu.atcrowdfunding.member.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("atcrowdfunding-activiti-service")
public interface ActivitiService {
	@RequestMapping("/act/completetask")
	public void completetask(@RequestBody Map<String, Object> map);

}
