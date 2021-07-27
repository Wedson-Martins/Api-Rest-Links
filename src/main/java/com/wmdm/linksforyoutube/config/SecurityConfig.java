package com.wmdm.linksforyoutube.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String ADMIN = "ADMIN";
	private static final String USER = "USER";
	private static final String TEST = "TEST";

	@Autowired
	private UserDetailsService userDetailsService;

	
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/authenticate").permitAll()
		.antMatchers("/").permitAll()
		.antMatchers("/users/**").hasRole(ADMIN)
		.antMatchers("/roles/**").hasAnyRole(ADMIN)
		.antMatchers("/links/**").hasAnyRole(ADMIN)
		.and()
		.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**", "/js/**", "/css/**", "/videos/**", "/images/**", "/resources/**");
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}












//
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import
// org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.builders.WebSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.password.NoOpPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
//
// @Configuration
// public class SecuritConfig extends WebSecurityConfigurerAdapter {
//
// @Override
// protected void configure(AuthenticationManagerBuilder auth) throws Exception
// {
// auth.inMemoryAuthentication()
// .withUser("wedson").password("123").roles("admin");
// }
//
// @Override
// protected void configure(HttpSecurity http) throws Exception {
//// http.csrf().disable()
//// .authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//// .antMatchers("/login").permitAll()
//// .anyRequest().authenticated()
//// .and()
//// .formLogin().permitAll()
//// .and()
//// .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
////
//
// http.authorizeRequests()
// .antMatchers("/getlinks").permitAll()
//
// .antMatchers("/users/**").permitAll()
// .antMatchers("/roles/**").permitAll()
//
// .anyRequest().authenticated()
// .and()
// .csrf().disable()
// .httpBasic()
// .and()
// .sessionManagement()
// .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//
// }
//
// @Override
// public void configure(WebSecurity web) throws Exception {
// // TODO Auto-generated method stub
// super.configure(web);
// }
//
//
//
// @Bean
// public PasswordEncoder passwordEncoder() {
// return NoOpPasswordEncoder.getInstance();
// }
//
//
//
//
// }
