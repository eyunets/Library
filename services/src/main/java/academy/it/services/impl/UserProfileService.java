package academy.it.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.it.dao.UserProfileDAO;
import academy.it.entities.UserProfile;
import academy.it.services.IUserProfileService;

@Service
@Transactional
public class UserProfileService implements IUserProfileService {
	@Autowired
	UserProfileDAO userProfileDAO;

	@Override
	public UserProfile findByType(String type) {
		return userProfileDAO.findByType(type);
	}

	public List<UserProfile> findAll() {
		return userProfileDAO.findAll();
	}

	public UserProfile findById(Integer id) {

		return userProfileDAO.findById(id).orElse(null);
	}

	@Override
	public UserProfile save(UserProfile userProfile) {
		return userProfileDAO.save(userProfile);
	}

	@Override
	public UserProfile find(Integer id) {
		return userProfileDAO.findById((Integer) id).orElse(null);

	}

	@Override
	public void update(UserProfile userProfile) {
		userProfileDAO.save(userProfile);
	}

	@Override
	public void delete(Integer id) {
		userProfileDAO.deleteById((Integer) id);

	}

}
