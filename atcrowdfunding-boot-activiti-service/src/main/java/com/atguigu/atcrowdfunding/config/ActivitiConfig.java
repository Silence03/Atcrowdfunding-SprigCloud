package com.atguigu.atcrowdfunding.config;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActivitiConfig implements ProcessEngineConfigurationConfigurer{

	@Override
	public void configure(SpringProcessEngineConfiguration processEngineConfiguration) {
		processEngineConfiguration.setLabelFontName("宋体");
		processEngineConfiguration.setActivityFontName("宋体");
		processEngineConfiguration.setMailServerPort(25);
	}
	
}