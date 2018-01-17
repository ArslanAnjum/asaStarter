package com.arslan.asaStarter.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.inMemoryAuthentication()
			.withUser("admin")
			.password("123")
			.roles("admin");
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers(
				"/login",
				"/webResources/**",
				"/app/**").permitAll()
			.antMatchers("/**").authenticated()
			.and().formLogin()
				  .loginPage("/login").permitAll()
				  .usernameParameter("username")
				  .passwordParameter("password")
			.and().csrf()
			.and().exceptionHandling().accessDeniedPage("/accessDenied");
		
		http.sessionManagement()
			.maximumSessions(100)
			.expiredUrl("/login.html");
	}

}
