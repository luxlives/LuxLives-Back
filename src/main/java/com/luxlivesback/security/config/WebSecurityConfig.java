package com.luxlivesback.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.luxlivesback.security.JwtAuthenticationEntryPoint;
import com.luxlivesback.security.filter.JwtAuthenticationTokenFilter;

/**
 * @Configuration -> sinaliza que é uma classe de configuração;
 * @EnableWebSecurity -> habilita o security na aplicação, validando todas as requisições;
 * @EnableGlobalMethodSecurity -> realiza validações por métodos utilizando-se do pre-autorized.
 * */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	/**
	 * Gerenciador de senha utilizando o BCryptPasswordEncoder.
	 * */
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * JwtAuthenticationTokenFilter interceptará todas as requisições.
	 * */
	
	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
		return new JwtAuthenticationTokenFilter(); 
	}
	
	/**
	 * Aqui determinamos os dominios que estarão liberados a realizar
	 * requisições a API.
	 * 
	 * */
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurer() {
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/**");      
	        }
	    };
	}
	
	/**
	 * Esse emplementação em AuthenticationManager é necessária a partir do spring 2
	 * para o sungionamento da API
	 * */
	
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	   return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().and()
			.csrf()
				.disable()
				.exceptionHandling()
				.authenticationEntryPoint(unauthorizedHandler)
				.and()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
			.authorizeRequests()
				.antMatchers("/auth/**",
						"/usuarios/cadastro/**",
						"/v2/api-docs",
                        "/configuration/**",
                        "/swagger*/**",
                        "/webjars/**",
                        "/h2-console/**")
				.permitAll()
				.anyRequest()
				.authenticated();
		httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
		httpSecurity.headers().cacheControl();
	}

}
