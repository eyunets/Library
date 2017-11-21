package academy.it.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.it.dao.BookDAO;
import academy.it.entities.Book;
import academy.it.services.IBookService;

@Service
@Transactional
public class BookServiceImpl implements IBookService {

	private static final int PAGE_SIZE = 10;
	@Autowired
	private BookDAO bookDAO;

	private BookServiceImpl() {
	}

	@Override
	@Cacheable(value = "books")
	public Page<Book> getBookPage(Integer pageNumber) {
		PageRequest pageRequest = PageRequest.of(pageNumber - 1, PAGE_SIZE);
		return bookDAO.findAll(pageRequest);
	}

	@Override
	@Cacheable(value = "search")
	public Page<Book> getSearchPage(String name, Integer pageNumber) {
		PageRequest pageRequest = PageRequest.of(pageNumber - 1, PAGE_SIZE);
		return bookDAO.findByName(name, pageRequest);
	}

	@Override
	@Caching(evict = { @CacheEvict(value = "books", allEntries = true),
			@CacheEvict(value = "search", allEntries = true) })
	public Book save(Book book) {
		return bookDAO.save(book);
	}

	@Override
	public Book find(Integer id) {
		return bookDAO.findById((Integer) id).orElse(null);
	}

	@Override
	@Caching(evict = { @CacheEvict(value = "books", allEntries = true),
			@CacheEvict(value = "search", allEntries = true) })
	public void update(Book book) {
		bookDAO.save(book);
	}

	@Override
	public void update(Book oldBook, Book newBook) {
		oldBook.setName((newBook.getName().length() == 0) ? oldBook.getName() : newBook.getName());
		oldBook.setIsbn((newBook.getIsbn().length() == 0) ? oldBook.getIsbn() : newBook.getIsbn());
		oldBook.setGenre((newBook.getGenre().length() == 0) ? oldBook.getGenre() : newBook.getGenre());
		oldBook.setYear((newBook.getYear() == 0) ? oldBook.getYear() : newBook.getYear());
		oldBook.setAuthors((newBook.getAuthors().size() == 0) ? oldBook.getAuthors() : newBook.getAuthors());
		update(oldBook);
	}

	@Override
	@Caching(evict = { @CacheEvict(value = "books", allEntries = true),
			@CacheEvict(value = "search", allEntries = true) })
	public void delete(Integer id) {
		bookDAO.deleteById((Integer) id);
	}

	@Override
	public List<Book> findByName(String name) {
		return bookDAO.findByName(name);
	}

	@Override
	public Book findByIsbn(String isbn) {
		return bookDAO.findByIsbn(isbn);
	}

	@Override
	public List<Book> findByGenre(String genre) {
		return bookDAO.findByGenre(genre);
	}

	@Override
	public List<Book> findAll() {
		return (List<Book>) bookDAO.findAll();
	}

}
