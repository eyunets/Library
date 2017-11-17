package academy.it.services;

import java.util.List;

import academy.it.entities.User;

public interface IUserService extends IService<User> {

	List<User> findBySurname(String surname);

	User findByLogin(String login);

	void update(User oldUser, User newUser);

	List<User> findAllUsers();
}
