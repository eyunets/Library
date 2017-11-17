package academy.it.services;

import java.util.List;

import academy.it.entities.Book;
import academy.it.entities.Form;
import academy.it.entities.User;

public interface IFormService extends IService<Form> {

	List<Form> findByUser(User user);

	List<Form> findByBook(Book book);

}
