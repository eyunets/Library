package academy.it.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class RootController {

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/home?logout";
	}

	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public String accessDenied(ModelMap model) {
		model.addAttribute("pageName", "accessdenied");
		return "accessDenied";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String redirect() {
		return "redirect:/home";
	}

	@RequestMapping(value = "errors", method = RequestMethod.GET)
	public String showErrorPage(ModelMap model, HttpServletRequest request) {

		int errorCode = getErrorCode(request);

		if (errorCode == 404) {
			model.addAttribute("pageName", "404");
			model.addAttribute("errorMsg", "Http Error Code: 404. Resource not found");
			return "400";
		} else {
			model.addAttribute("pageName", "error");
			model.addAttribute("errorMsg", "Http Error Code: 500. Internal Server Error");
			return "error";
		}
	}

	private int getErrorCode(HttpServletRequest httpRequest) {
		return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
	}
}
