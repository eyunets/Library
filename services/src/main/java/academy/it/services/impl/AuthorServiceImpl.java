package academy.it.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.it.dao.AuthorDAO;
import academy.it.entities.Author;
import academy.it.services.IAuthorService;

@Service
@Transactional
public class AuthorServiceImpl implements IAuthorService {
	@Autowired
	private AuthorDAO authorDAO;

	private AuthorServiceImpl() {
	}

	@Override
	@CacheEvict(value = "authors", allEntries = true)
	public Author save(Author author) {
		return authorDAO.save(author);
	}

	@Override
	public Author find(Integer id) {
		return authorDAO.findById((Integer) id).orElse(null);
	}

	@Override
	@CacheEvict(value = "authors", allEntries = true)
	public void update(Author author) {
		authorDAO.save(author);
	}

	@Override
	@CacheEvict(value = "authors", allEntries = true)
	public void delete(Integer id) {
		authorDAO.deleteById((Integer) id);

	}

	@Override
	public List<Author> findBySurname(String surname) {
		return authorDAO.findBySurname(surname);
	}

	@Override
	@Cacheable(value = "authors")
	public List<Author> findAll() {
		return (List<Author>) authorDAO.findAll();

	}

	@Override
	public boolean isExists(Author author) {
		return authorDAO.existsByNameAndSurnameAndSecondName(author.getName(), author.getSurname(),
				author.getSecondName());
	}
}
