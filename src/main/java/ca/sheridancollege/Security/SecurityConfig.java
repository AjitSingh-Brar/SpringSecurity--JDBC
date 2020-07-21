package ca.sheridancollege.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LogingingAccessDeniedHandler accessDeniedHandler;
	
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests()
		.antMatchers("/user/**").hasRole("ADMIN")
		.antMatchers("/Manager/**").hasAnyRole("MANAGER","ADMIN")
		.antMatchers("/Owner/**").hasAnyRole("OWNER","ADMIN")
		.antMatchers("/Employee/**").hasAnyRole("EMPLOYEE","ADMIN")
		.antMatchers("/",
				"/js/**",
				"/css/**",
				"/img/**",
				"/**").permitAll()
		
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
		.logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout")
         .permitAll()	
		.and()
		.exceptionHandling()
		.accessDeniedHandler(accessDeniedHandler);
	}
	
	@Bean 
	
	public BCryptPasswordEncoder passwordEncorder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	UserDetailedServiceImpl userDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncorder());
	}
	
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception
//	{
//		auth.inMemoryAuthentication()
//		.passwordEncoder(NoOpPasswordEncoder.getInstance())
//		.withUser("admin").password("password").roles("ADMIN")
//		.and()
//		.withUser("manager").password("manager").roles("MANAGER")
//		.and()
//		.withUser("owner").password("owner").roles("OWNER")
//		.and()
//		.withUser("employee").password("employee").roles("EMPLOYEE");
//	
//	}

}
