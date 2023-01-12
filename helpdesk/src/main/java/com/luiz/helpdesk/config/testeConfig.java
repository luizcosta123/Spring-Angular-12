package com.luiz.helpdesk.config;

import com.luiz.helpdesk.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class testeConfig {
	
	@Autowired
	private DbService dbService;
	
	@Bean
	public void instanciaDb() {
		dbService.instanciaDb();
	}

}
