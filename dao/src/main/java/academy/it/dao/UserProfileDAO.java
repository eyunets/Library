package academy.it.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import academy.it.entities.UserProfile;

@Repository
public interface UserProfileDAO extends JpaRepository<UserProfile, Integer> {

	UserProfile findByType(String type);

}
