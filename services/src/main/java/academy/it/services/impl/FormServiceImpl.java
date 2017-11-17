package academy.it.services.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.it.dao.FormDAO;
import academy.it.entities.Book;
import academy.it.entities.Form;
import academy.it.entities.User;
import academy.it.services.IFormService;
import academy.it.services.ServiceException;

@Service
@Transactional
public class FormServiceImpl implements IFormService {

	@Autowired
	private FormDAO formDAO;

	private FormServiceImpl() {
	}

	@Override
	public Form save(Form form) {
		return formDAO.save(form);
	}

	@Override
	public Form find(Integer id) {
		return formDAO.findById((Integer) id).orElse(null);
	}

	@Override
	public void update(Form form) {
		formDAO.save(form);
	}

	@Override
	public void delete(Integer id) {
		formDAO.deleteById((Integer) id);
	}

	@Override
	public List<Form> findByUser(User user) {
		return formDAO.findByUser(user);
	}

	@Override
	public List<Form> findByBook(Book book) {
		return formDAO.findByBook(book);

	}

	@Override
	public List<Form> findAll() {
		try {

			return (List<Form>) (formDAO.findAll());

		} catch (HibernateException e) {
			throw new ServiceException("Error finding Form", e);
		}
	}
}
