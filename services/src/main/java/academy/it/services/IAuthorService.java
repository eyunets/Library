package academy.it.services;

import java.util.List;

import academy.it.entities.Author;

public interface IAuthorService extends IService<Author> {

	List<Author> findBySurname(String surname);

	boolean isExists(Author author);

}
