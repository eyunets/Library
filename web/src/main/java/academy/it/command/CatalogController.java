package academy.it.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import academy.it.entities.Book;
import academy.it.services.IBookService;

@Controller
@RequestMapping("/catalog")
public class CatalogController {
	public static final String MAIN = "catalog/main";

	@Autowired
	private IBookService bookService;

	@RequestMapping(value = "/books/{pageNumber}", method = RequestMethod.GET)
	public String mainPage(@PathVariable Integer pageNumber, ModelMap model) {
		Page<Book> page = bookService.getBookPage(pageNumber);
		// ArrayList<Book> books = new ArrayList<>(page.getContent());
		int current = page.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, page.getTotalPages());
		model.addAttribute("page", page);
		// model.addAttribute("books", books);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("pageName", "catalog");
		return MAIN;
	}

	@RequestMapping(value = "/search/{pageNumber}", method = RequestMethod.GET)
	public String searchPage(@PathVariable Integer pageNumber, @RequestParam("name") String name, ModelMap model) {
		Page<Book> page = bookService.getSearchPage(name, pageNumber);
		// ArrayList<Book> books = new ArrayList<>(page.getContent());
		int current = page.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, page.getTotalPages());
		model.addAttribute("page", page);
		// model.addAttribute("books", books);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("pageName", "catalog");
		return MAIN;
	}
}
