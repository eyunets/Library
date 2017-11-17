package academy.it.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.it.dao.UserDAO;
import academy.it.entities.User;
import academy.it.entities.enums.UserProfileType;
import academy.it.services.IUserService;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDAO userDAO;

	private UserServiceImpl() {
	}

	@Override
	public User save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userDAO.save(user);
	}

	@Override
	public User find(Integer id) {
		return userDAO.findById((Integer) id).orElse(null);
	}

	@Override
	public void update(User user) {
		userDAO.save(user);
	}

	@Override
	public void update(User oldUser, User newUser) {
		oldUser.setSurname((newUser.getSurname().length() == 0) ? oldUser.getSurname() : newUser.getSurname());
		oldUser.setName((newUser.getName().length() == 0) ? oldUser.getName() : newUser.getName());
		// oldUser.setEmail((newUser.getEmail().length() == 0) ? oldUser.getEmail() :
		// newUser.getEmail());
		oldUser.setPassword((newUser.getPassword().length() == 0) ? oldUser.getPassword()
				: (passwordEncoder.encode(newUser.getPassword())));
		update(oldUser);
	}

	@Override
	public void delete(Integer id) {
		userDAO.deleteById((Integer) id);
	}

	@Override
	public List<User> findBySurname(String surname) {
		return userDAO.findBySurname(surname);
	}

	@Override
	public User findByLogin(String login) {
		ArrayList<User> users = new ArrayList<>(userDAO.findByEmail(login));
		if (users.isEmpty())
			return null;
		else
			return users.get(0);
	}

	@Override
	public List<User> findAllUsers() {
		ArrayList<String> types = new ArrayList<>();
		types.add(UserProfileType.USER.toString());
		types.add(UserProfileType.BANNED.toString());
		return userDAO.findBySpecificRoles(types);
	}

	@Override
	public List<User> findAll() {
		return userDAO.findAll();
	}
}
