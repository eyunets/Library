package academy.it.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import academy.it.entities.Author;
import academy.it.services.IAuthorService;

@Component
public class AuthorConverter implements Converter<Object, Author> {

	@Autowired
	IAuthorService authorService;

	public Author convert(Object element) {
		Integer id = Integer.parseInt((String) element);
		Author author = authorService.find(id);
		return author;
	}
}