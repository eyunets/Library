package academy.it.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import academy.it.entities.Author;

@Repository
public interface AuthorDAO extends JpaRepository<Author, Integer> {
	List<Author> findBySurname(String surname);

	boolean existsByNameAndSurnameAndSecondName(String name, String surname, String SecondName);
}
