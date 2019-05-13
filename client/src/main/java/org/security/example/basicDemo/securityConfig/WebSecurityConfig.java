package org.security.example.basicDemo.securityConfig;


import org.easySecurity.core.user.OAuth2ClientUserService;
import org.easySecurity.core.voters.AddVotersToFilterSecurityInterceptorHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder passwordEncoder;


	@Autowired
	private OAuth2AuthorizedClientService oAuth2AuthorizedClientService;


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
					.oauth2Login()
				        .loginPage("/login/end/fail")
				   			.authorizationEndpoint()
								.baseUri("/OAuth/login/process")
						.and()
							.userInfoEndpoint()
								.userService(new OAuth2ClientUserService())
				        .and()
							.defaultSuccessUrl("/login/end/success")
				        .permitAll()
				.and()
					.oauth2Client()
					.authorizedClientService(oAuth2AuthorizedClientService)
					.authorizationCodeGrant()
						.authorizationRequestRepository(this.cookieAuthorizationRequestRepository());
	}

	@Override
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	private AuthorizationRequestRepository<OAuth2AuthorizationRequest> cookieAuthorizationRequestRepository() {
		return new HttpSessionOAuth2AuthorizationRequestRepository ();
	}


}
