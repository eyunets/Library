package academy.it.command;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String welcomePage(ModelMap model) {
		model.addAttribute("pageName", "main");
		return "home";
	}
}
