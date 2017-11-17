package academy.it.services;

import java.util.List;

import academy.it.entities.UserProfile;

public interface IUserProfileService extends IService<UserProfile> {
	UserProfile findByType(String type);

	public UserProfile findById(Integer id);

	List<UserProfile> findAll();
}
