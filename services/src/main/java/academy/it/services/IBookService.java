package academy.it.services;

import java.util.List;

import org.springframework.data.domain.Page;

import academy.it.entities.Book;

public interface IBookService extends IService<Book> {

	List<Book> findByName(String name);

	Page<Book> getSearchPage(String name, Integer pageNumber);

	Book findByIsbn(String isbn);

	List<Book> findByGenre(String genre);

	void update(Book oldBook, Book newBook);

	Page<Book> getBookPage(Integer pageNumber);

}
