package com.wmdm.linksforyoutube.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecuritConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("wedson").password("123").roles("admin");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable()
//		.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//		.antMatchers("/login").permitAll()
//		.anyRequest().authenticated()
//		.and()
//		.formLogin().permitAll()
//		.and()
//		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
//		
		
           http.authorizeRequests()
           .antMatchers("/getlinks").permitAll()
          
           .antMatchers("/users/**").permitAll()
           .antMatchers("/roles/**").permitAll()
          
           .anyRequest().authenticated()
           .and()
           .csrf().disable()
           .httpBasic()
           .and()
           .sessionManagement()
                   .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      
		
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		super.configure(web);
	}

	

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

	
	
	
}
