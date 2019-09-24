package com.packt.volunteerhoursrecorder.config;

import java.util.Arrays;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.packt.volunteerhoursrecorder.security.AuthenticationFilter;
import com.packt.volunteerhoursrecorder.security.LoginFilter;
import com.packt.volunteerhoursrecorder.service.CustomUserDetailsService;
import com.packt.volunteerhoursrecorder.service.JwtAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)

public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	       http
           .cors()
               .and()
           .csrf()
               .disable()
           .exceptionHandling()
               .authenticationEntryPoint(unauthorizedHandler)
               .and()
           .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and()
           .authorizeRequests()
               .antMatchers("/",
                   "/favicon.ico",
                   "/**/*.png",
                   "/**/*.gif",
                   "/**/*.svg",
                   "/**/*.jpg",
                   "/**/*.html",
                   "/**/*.css",
                   "/**/*.js")
                   .permitAll()
               .antMatchers("/h2-console/**")
                   .permitAll()
               .antMatchers("/api/auth/**")
                   .permitAll()
               .antMatchers("/api/user/checkUsernameAvailability", "/api/user/checkEmailAvailability")
                   .permitAll()
               .anyRequest()
                   .authenticated()
          .and().headers().frameOptions().sameOrigin();

		   // Add our custom JWT security filter
		   http.addFilterBefore(
				   new LoginFilter("/api/auth/signin",authenticationManagerBean()), 
				   UsernamePasswordAuthenticationFilter.class
				   );
		   http.addFilterBefore(
				   new AuthenticationFilter(), 
				   UsernamePasswordAuthenticationFilter.class
				   );

	}

	// SecurityConfig.java
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("*"));
		config.setAllowedMethods(Arrays.asList("*"));
		config.setAllowedHeaders(Arrays.asList("*"));
		config.setAllowCredentials(true);
		config.applyPermitDefaultValues();
		source.registerCorsConfiguration("/**", config);
		return source;
	}

}
