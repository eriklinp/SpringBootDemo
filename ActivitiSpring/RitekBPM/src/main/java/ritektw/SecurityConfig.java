package ritektw;

import org.activiti.engine.IdentityService;
import org.activiti.spring.security.IdentityServiceUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * 使用Spring Security 結合 Activiti User 管理
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		// super.configure(auth);
		PasswordEncoder pwdEncoder = new BCryptPasswordEncoder();

		auth.inMemoryAuthentication() // 驗證資訊存放於記憶體
				.passwordEncoder(pwdEncoder).withUser("admin").password(pwdEncoder.encode("admin99"))
				.roles("ADMIN", "MEMBER").and()
				.withUser("caterpillar").password(pwdEncoder.encode("1234"))
				.roles("MEMBER");
		
		auth.userDetailsService(userDetailsService());
	}

	@Autowired
	private IdentityService identityService;

//	private AuthenticationManager authenticationManager;
//
//	@Bean
//	public AuthenticationManager customAuthenticationManager() throws Exception {
//		return authenticationManager();
//	}
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
      throws Exception {
        auth.inMemoryAuthentication().withUser("mkyong").password("123456").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("{noop}123456").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("dba").password("123456").roles("DBA");
        
        auth.userDetailsService(userDetailsService());
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')").antMatchers("/*")
				.access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
				.and()
			.formLogin()
				.usernameParameter("username")
				.passwordParameter("password")
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/index")
				.permitAll()
				.and()
			.httpBasic()
				.disable();
		
		
		http.csrf().disable();

	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new IdentityServiceUserDetailsService(this.identityService);
	}

	// spring security configuration
}
