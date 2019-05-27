package org.security.example.basicDemo;

import org.easySecutity.client.config.EnableEasySecurityClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableEasySecurityClientConfig
public class BasicDemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BasicDemoApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BasicDemoApplication.class);
	}
}
