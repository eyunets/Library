package academy.it.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.antMatchers("/", "/home", "/about", "/catalog/**", "/books/id={bookID}").permitAll()
				.antMatchers("/signup/**").anonymous()
				.antMatchers("/users/all", "/users/id={userID}/ban", "/users/{userID}", "/users", "/author/add",
						"/books/add", "/books/id={bookID}/edit", "/books/id={bookID}/delete")
				.access("hasRole('ADMIN')")
				.antMatchers("/users/edit", "/users/books", "/books/id={bookID}/bookmark)",
						"/users/forms/id={formID}/delete")
				.access("hasRole('ADMIN') or hasRole('USER')").and().httpBasic().realmName("Realm")
				.authenticationEntryPoint(getBasicAuthEntryPoint()).and().formLogin().loginPage("/login")
				.loginProcessingUrl("/login").usernameParameter("email").passwordParameter("password")
				.defaultSuccessUrl("/home").and().exceptionHandling().accessDeniedPage("/accessDenied");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public MyBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
		return new MyBasicAuthenticationEntryPoint();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

}