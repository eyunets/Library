package dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import academy.it.dao.UserDAO;
import academy.it.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-data.xml")
public class UserDaoTest {
	@Autowired
	UserDAO userDao;

	@Test
	public void jpaRepositoryTest() {
		User user1 = new User(1, "Evgenii", "Yunets", "password", "email", null, null);
		userDao.save(user1);
		System.out.println(user1);
		User user = userDao.findById(1).orElse(new User());
		System.out.println(user);
		user.setName("New name" + user.getName());
		userDao.save(user);
		User newUser = userDao.findById(1).orElse(new User());
		System.out.println(newUser);
	}
}