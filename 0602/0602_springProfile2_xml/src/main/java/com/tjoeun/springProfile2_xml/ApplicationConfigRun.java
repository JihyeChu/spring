package com.tjoeun.springProfile2_xml;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("run")
@Configuration
public class ApplicationConfigRun {

	@Bean
	public ServerInfo serverInfo() {
		ServerInfo serverInfo = new ServerInfo();
		serverInfo.setIpNumber("192.167.0.100");
		serverInfo.setPortNumber("8080");
		return serverInfo;
	}
	
}
