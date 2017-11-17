package academy.it.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import academy.it.entities.Book;
import academy.it.entities.Form;
import academy.it.entities.User;

@Repository
public interface FormDAO extends JpaRepository<Form, Integer> {

	List<Form> findByUser(User user);

	List<Form> findByBook(Book book);

}
