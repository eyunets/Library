package academy.it.command;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import academy.it.entities.Book;
import academy.it.entities.Form;
import academy.it.services.IAuthorService;
import academy.it.services.IBookService;
import academy.it.services.IFormService;
import academy.it.services.IUserService;

@Controller
@RequestMapping("/books")
@SessionAttributes("authors")
public class BookController {
	@Autowired
	private IAuthorService authorService;
	@Autowired
	private IBookService bookService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IFormService formService;
	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addBookPage(ModelMap model) {
		Book book = new Book();
		model.addAttribute("authors", authorService.findAll());
		model.addAttribute("book", book);
		model.addAttribute("edit", false);
		model.addAttribute("pageName", "addbook");
		return "addbook";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addBook(Locale locale, @Valid Book book, BindingResult br, ModelMap model) {

		if (book.getName() == null) {
			FieldError loginError = new FieldError("book", "name", messageSource.getMessage("type.name", null, locale));
			br.addError(loginError);
		}

		if (book.getIsbn() != null) {
			if (!bookService.findByIsbn(book.getIsbn()).isEmpty()) {
				FieldError loginError = new FieldError("book", "isbn",
						messageSource.getMessage("unique.isbn", null, locale));
				br.addError(loginError);
			}
		} else {
			FieldError loginError = new FieldError("book", "isbn",
					messageSource.getMessage("input.wrong", null, locale));
			br.addError(loginError);

		}
		if (book.getAuthors().isEmpty()) {
			FieldError loginError = new FieldError("book", "Authors",
					messageSource.getMessage("choose.author", null, locale));
			br.addError(loginError);
		}
		if (book.getYear() != null) {
			if (book.getYear() >= LocalDate.now().getYear() || book.getYear() < 0) {
				FieldError loginError = new FieldError("book", "year",
						messageSource.getMessage("input.wrong", null, locale));
				br.addError(loginError);
			}
		} else {
			FieldError loginError = new FieldError("book", "year",
					messageSource.getMessage("input.wrong", null, locale));
			br.addError(loginError);
		}

		if (!br.hasErrors()) {
			if (book != null) {
				book = bookService.save(book);
			}
			return "redirect:/catalog/books/1";
		} else {
			model.addAttribute("pageName", "addbook");
			return "addbook";
		}
	}

	@RequestMapping(value = "/id={bookID}/edit", method = RequestMethod.GET)
	public String editBookPage(ModelMap model, @PathVariable Integer bookID) {
		Book book = bookService.find(bookID);
		model.addAttribute("authors", authorService.findAll());
		model.addAttribute("book", book);
		model.addAttribute("pageName", "editBook");
		return "editBook";
	}

	@RequestMapping(value = "/id={bookID}/edit", method = RequestMethod.POST)
	public String editBook(Locale locale, @Valid Book book, @PathVariable Integer bookID, BindingResult br,
			ModelMap model) {

		if (book.getIsbn() != null) {
			if (!bookService.findByIsbn(book.getIsbn()).isEmpty()) {
				FieldError loginError = new FieldError("book", "isbn",
						messageSource.getMessage("unique.isbn", null, locale));
				br.addError(loginError);
			}
		}

		if (book.getYear() != null) {
			if (book.getYear() >= LocalDate.now().getYear() || book.getYear() < 0) {
				FieldError loginError = new FieldError("book", "year",
						messageSource.getMessage("input.wrong", null, locale));
				br.addError(loginError);
			}
		}

		if (!br.hasErrors()) {
			if (book != null) {
				bookService.update(bookService.find(bookID), book);
			}
			return "redirect:/catalog/books/1";
		} else {
			model.addAttribute("pageName", "editBook");
			return "editBook";
		}
	}

	@RequestMapping(value = "/id={bookID}", method = RequestMethod.GET)
	public String bookPage(@PathVariable String bookID, ModelMap model) {
		Book book = bookService.find(Integer.parseInt(bookID));
		boolean valid = false;
		if (book != null) {
			Set<Form> forms = userService.findByLogin(getPrincipal()).getForms();
			for (Form form : forms) {
				if (book.getBookID() == form.getBook().getBookID())
					valid = true;
			}
			model.addAttribute("book", book);
			model.addAttribute("pageName", "book");
			model.addAttribute("valid", valid);
			return "book";
		} else {
			return "redirect:/catalog/books/1";
		}
	}

	@RequestMapping(value = "/id={bookID}/bookmark", method = RequestMethod.POST)
	public String addToBookmarks(@PathVariable String bookID, Book book, ModelMap model) {
		book = bookService.find(Integer.parseInt(bookID));
		Form form = new Form();
		form.setBook(book);
		form.setUser(userService.findByLogin(getPrincipal()));
		formService.save(form);
		return "redirect:/books/id={bookID}";
	}

	@RequestMapping(value = "/id={bookID}/delete", method = RequestMethod.POST)
	public String deleteUserBook(@PathVariable Integer bookID, ModelMap model) {
		bookService.delete(bookID);
		return "redirect:/catalog/books/1";
	}

	private String getPrincipal() {
		String userEmail = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userEmail = ((UserDetails) principal).getUsername();
		} else {
			userEmail = principal.toString();
		}
		return userEmail;
	}

}
