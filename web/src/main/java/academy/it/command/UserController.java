package academy.it.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
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

import com.google.gson.Gson;

import academy.it.entities.Form;
import academy.it.entities.User;
import academy.it.entities.UserProfile;
import academy.it.services.IFormService;
import academy.it.services.IUserProfileService;
import academy.it.services.IUserService;

@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	private IUserService userService;
	@Autowired
	private IFormService formService;

	@Autowired
	IUserProfileService userProfileService;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editPage(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		model.addAttribute("pageName", "edituser");
		return "userEdit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editPagePost(Locale locale, ModelMap model, @Valid User user, BindingResult br) {
		if (user.getPassword().length() != 0) {
			if (!user.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
				FieldError loginError = new FieldError("user", "password",
						messageSource.getMessage("error.passwordpattern", null, locale));
				br.addError(loginError);
			}
		}

		if (!br.hasErrors()) {
			if (user != null) {
				userService.update(userService.findByLogin(getPrincipal()), user);
			}
			return "redirect:/home";
		} else
			model.addAttribute("pageName", "edituser");
		return "userEdit";

	}

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String userBooks(ModelMap model) {
		Set<Form> forms = userService.findByLogin(getPrincipal()).getForms();
		model.addAttribute("forms", forms);
		model.addAttribute("pageName", "mybooks");
		return "userBooks";
	}

	@RequestMapping(value = "/forms/id={formID}/delete", method = RequestMethod.POST)
	public void deleteUserBook(@PathVariable Integer formID, ModelMap model, HttpServletResponse response)
			throws IOException {
		formService.delete(formID);
		Set<Form> forms = userService.findByLogin(getPrincipal()).getForms();
		model.addAttribute("forms", forms);
		PrintWriter writer = response.getWriter();
		writer.print(new Gson().toJson("OK"));
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String userAll(ModelMap model) {
		ArrayList<User> users = new ArrayList<>(userService.findAllUsers());
		model.addAttribute("users", users);
		model.addAttribute("pageName", "users");
		return "usersAll";
	}

	@RequestMapping(value = "/id={userID}/ban", method = RequestMethod.POST)
	public void banUser(@PathVariable Integer userID, ModelMap model, HttpServletResponse response) throws IOException {
		User user = userService.find(userID);
		System.out.println(user.getUserProfile().getType());
		if (user.getUserProfile().getType().equals("USER")) {
			UserProfile userProfile = userProfileService.findByType("BANNED");
			user.setUserProfile(userProfile);
			userService.update(user);
		} else {
			UserProfile userProfile = userProfileService.findByType("USER");
			user.setUserProfile(userProfile);
			userService.update(user);
		}
		PrintWriter writer = response.getWriter();
		writer.print(new Gson().toJson("OK"));
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