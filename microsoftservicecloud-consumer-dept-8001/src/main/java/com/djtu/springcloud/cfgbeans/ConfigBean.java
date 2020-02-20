package com.djtu.springcloud.cfgbeans;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;

@Configuration
public class ConfigBean {
	/**
	 * boot-->spring applicationContext.xml ---@Configuration配置
	 * configBean=applicationContext.xml
	 * 
	 * @Bean public UserService getUserservice(){ return new UserServiceImpl(); }
	 *       applicationContext.xml相当于ConfigBean(@Configuration)
	 *       <bean id="userService" class="com.djut.twh.UserServiceImpl">
	 */
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public IRule myRule() {
		return new RoundRobinRule();
	}

}
