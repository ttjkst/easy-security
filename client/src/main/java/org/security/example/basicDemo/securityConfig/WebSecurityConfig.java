package org.security.example.basicDemo.securityConfig;


import org.easySecutity.client.dsl.ClientDsl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/**
		 * 另一种身份认证（非权限校验）
		 * */
		http
				.csrf()
				.disable()
						.authorizeRequests()
						.anyRequest()
						.authenticated()
                .and()
					.authorizeRequests()
				    //.withObjectPostProcessor(new AddVotersToFilterSecurityInterceptorHelper())
					.antMatchers("oauth2/**")
					.hasRole("ADMIN")
				    .antMatchers("/OAuth/login/**","/OAuth/login/process/**","/login/end/fail")
				    .permitAll()
				.and()
					.apply(new ClientDsl());
	}

	@Override
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
}
