package com.atguigu.atcrowdfunding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement  //开启事务
@MapperScan("com.atguigu.atcrowdfunding.**.dao") //开启接口扫描
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class AtcrowdfundingBootMemberServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtcrowdfundingBootMemberServiceApplication.class, args);
	}
}
