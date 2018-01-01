package com.atguigu.atcrowdfunding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.FeignClient;


@FeignClient
@EnableDiscoveryClient
@SpringBootApplication
public class AtcrowdfundingBootManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtcrowdfundingBootManagerApplication.class, args);
	}
}