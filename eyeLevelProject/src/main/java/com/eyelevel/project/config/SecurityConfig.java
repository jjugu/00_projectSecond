package com.eyelevel.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.eyelevel.project.category.service.StudentProfileService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	   private StudentProfileService studentService;
	   
	   @Autowired
	   public SecurityConfig(StudentProfileService memberService) {
	      this.studentService = memberService;
	   }
	   
	   @Bean
	   public BCryptPasswordEncoder passwordEncoder() {
	      return new BCryptPasswordEncoder();
	   }
	   
	   @Bean
	   public WebSecurityCustomizer webSecurityCustomizer() {
	      return (web) -> web.ignoring()
	            .antMatchers("/css/**", "/js/**", "/images/**", "/lib/**", "/image/**");
	   }
	   @Bean
	   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		   
	      http.csrf().disable()
	      
	      	/* 권한별 요청 가능 경로 설정 권한이 없는 사용자가 url경로로 접근하는것을 막음*/
	         .authorizeRequests()
	         
	         
	         .antMatchers(HttpMethod.GET, "/student/personal").hasAnyRole("ADMIN","TEACHER","STUDENT")	
	         .antMatchers(HttpMethod.POST, "/student/personal").hasAnyRole("ADMIN","TEACHER","STUDENT")
	         .antMatchers(HttpMethod.GET, "/calendar/schedule").hasAnyRole("ADMIN","TEACHER","STUDENT")	
	         .antMatchers(HttpMethod.POST, "/calendar/schedule").hasAnyRole("ADMIN","TEACHER","STUDENT")
	         .antMatchers(HttpMethod.GET, "/question/searchList").hasAnyRole("ADMIN","TEACHER","STUDENT")	
	         .antMatchers(HttpMethod.POST, "/question/searchList").hasAnyRole("ADMIN","TEACHER","STUDENT")
	         
	         .antMatchers(HttpMethod.GET, "/profile/detail").hasAnyRole("ADMIN","TEACHER")	
	         .antMatchers(HttpMethod.POST, "/profile/detail").hasAnyRole("ADMIN","TEACHER")
	         .antMatchers(HttpMethod.GET, "/student/search").hasAnyRole("ADMIN","TEACHER")	
	         .antMatchers(HttpMethod.POST, "/student/search").hasAnyRole("ADMIN","TEACHER")
//	         .antMatchers(HttpMethod.GET, "/question/searchList").hasAnyRole("ADMIN","TEACHER")	
//	         .antMatchers(HttpMethod.POST, "/question/searchList").hasAnyRole("ADMIN","TEACHER")
	         .antMatchers(HttpMethod.GET, "/board/category").hasAnyRole("ADMIN","TEACHER")	
	         .antMatchers(HttpMethod.POST, "/board/category").hasAnyRole("ADMIN","TEACHER")
	         
	         .antMatchers(HttpMethod.GET, "/counseling/search").hasRole("ADMIN")	
	         .antMatchers(HttpMethod.POST, "/counseling/search").hasRole("ADMIN")
	         .antMatchers(HttpMethod.GET, "/student/regist").hasRole("ADMIN")	
	         .antMatchers(HttpMethod.POST, "/student/regist").hasRole("ADMIN")
	         .antMatchers(HttpMethod.GET, "/profile/regist").hasRole("ADMIN")	
	         .antMatchers(HttpMethod.POST, "/profile/regist").hasRole("ADMIN")
	         .antMatchers(HttpMethod.GET, "/profile/searchList").hasRole("ADMIN")	
	         .antMatchers(HttpMethod.POST, "/profile/searchList").hasRole("ADMIN")
	         
	         .antMatchers(HttpMethod.GET, "/counseling/search").hasRole("ADMIN")	
	         .antMatchers(HttpMethod.POST, "/counseling/search").hasRole("ADMIN")
	         .anyRequest().permitAll()									
	         .and()
	         .formLogin()									
	         .loginPage("/login/loginform")	
	         .successForwardUrl("/")									
	      	 .and()
	      	 	.logout()																
	      	 	.logoutRequestMatcher(new AntPathRequestMatcher("/login/logout"))		
	      	 	.deleteCookies("JSESSIONID")											
	      	 	.invalidateHttpSession(true)											 
	      	 	.logoutSuccessUrl("/")													 
	      	 .and()
	      	 	.exceptionHandling()													
	      	 	.accessDeniedPage("/admin/denied");									
	      	http.authenticationProvider(authenticationProvider());

	      return http.build();
	   }
	   
	   @Bean
	   public UserDetailsService userDetailsService() {
	      
	      return studentService;
	   }
	   
	   @Bean
	   public DaoAuthenticationProvider authenticationProvider() {
	      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	      
	      authProvider.setUserDetailsService(userDetailsService());
	      authProvider.setPasswordEncoder(passwordEncoder());
	      
	      return authProvider;
	   }
}