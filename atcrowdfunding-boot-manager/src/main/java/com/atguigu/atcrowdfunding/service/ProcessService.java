package com.atguigu.atcrowdfunding.service;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("atcrowdfunding-activiti-service")
public interface ProcessService {
	
	
	@RequestMapping("/act/querycount")
	public int querycount();

	@RequestMapping("/act/queryPageData")
	public List<Map<String, Object>> queryPageData(@RequestBody Map<String,Object> paramMap);
	

}
