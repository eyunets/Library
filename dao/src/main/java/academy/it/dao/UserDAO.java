package academy.it.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import academy.it.entities.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

	List<User> findBySurname(String surname);

	@Query("select u from User u inner join u.userProfile up where up.type in :types")
	List<User> findBySpecificRoles(@Param("types") List<String> types);

	List<User> findByEmail(String email);

}
