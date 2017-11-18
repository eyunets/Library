package dao;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import academy.it.dao.UserDAO;
import academy.it.entities.User;
import configuration.HibernateTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateTestConfig.class)
public class UserDaoTest {
	@Autowired
	UserDAO userDao;
	@Autowired
	DataSource dataSource;

	@Before
	public void setUp() throws Exception {
		IDatabaseConnection dbConn = new DatabaseDataSourceConnection(dataSource);
		DatabaseOperation.CLEAN_INSERT.execute(dbConn, getDataSet());
	}

	private IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("User.xml"));
		return dataSet;
	}

	@Test
	public void findById() {
		Assert.assertNotNull(userDao.findById(1));
		Assert.assertNull(userDao.findById(3).orElse(null));
	}

	@Test
	public void saveUser() {
		userDao.save(getNewUser());
		Assert.assertEquals(3, userDao.findAll().size());
	}

	@Test
	public void deleteUserId() {
		userDao.deleteById(1);
		Assert.assertEquals(1, userDao.findAll().size());
	}

	@Test
	public void findAllUsers() {
		Assert.assertEquals(2, userDao.findAll().size());
	}

	@Test
	public void findByEmail() {
		Assert.assertEquals("eyunets@outlook.com", userDao.findByEmail("eyunets@outlook.com").getEmail());
	}

	public User getNewUser() {
		User user1 = new User();
		user1.setUserID(3);
		user1.setEmail("kappa@outlook.by");
		user1.setName("New");
		user1.setSurname("New");
		user1.setPassword("Kappa123");
		user1.setForms(null);
		user1.setUserProfile(null);
		return user1;
	}
}