package academy.it.command;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import academy.it.entities.Author;
import academy.it.filters.AuthorFilter;
import academy.it.services.IAuthorService;

@Controller
@RequestMapping("/author")
public class AuthorController {
	@Autowired
	private IAuthorService authorService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	AuthorFilter authorFilter;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String signUpPage(ModelMap model) {
		Author author = new Author();
		model.addAttribute("author", author);
		model.addAttribute("edit", false);
		model.addAttribute("pageName", "authoradd");
		return "addauthor";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addBook(Locale locale, ModelMap model, Author author, BindingResult br) {
		authorFilter.checkAuthor(author, br, locale);
		if (!br.hasErrors()) {
			if (author != null) {
				author = authorService.save(author);

				model.put("author", author);
			}
			return "redirect:/books/add";
		} else {
			model.addAttribute("pageName", "authoradd");
			return "addauthor";
		}

	}

}
