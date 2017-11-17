package academy.it.services.auth;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.it.entities.User;
import academy.it.services.IUserService;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private IUserService userService;
	static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		User user = userService.findByLogin(login);
		logger.info("User : " + user);
		if (user == null) {
			logger.info("User not found");
			throw new UsernameNotFoundException("Username not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true, true,
				true, true, getGrantedAuthorities(user));
	}

	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		logger.info("UserProfile : " + user.getUserProfile());
		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserProfile().getType()));

		System.out.print("authorities :" + authorities);
		return authorities;
	}

}
