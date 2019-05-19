package org.oauth.authoriaztion.securityConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easySecurity.server.dsl.ServerDsl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Log logger = LogFactory.getLog(WebSecurityConfig.class);


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.apply(new ServerDsl().enableRoleToScope())
                .and()
                .formLogin()
                    .loginPage("/login/page")
                    .loginProcessingUrl("/login/processs")
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
