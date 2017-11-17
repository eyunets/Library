package academy.it.command;

import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import academy.it.entities.User;
import academy.it.entities.UserProfile;
import academy.it.services.IUserProfileService;
import academy.it.services.IUserService;

@Controller
public class SignUpController {
	@Autowired
	private IUserService userService;
	@Autowired
	IUserProfileService userProfileService;
	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signUpPage(ModelMap model) {
		User user = new User();

		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		model.addAttribute("pageName", "signup");
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signUp(Locale locale, ModelMap model, @Valid User user, BindingResult br, HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		UserProfile userProfile = userProfileService.findByType("USER");
		if (userProfile == null) {
			userProfile = userProfileService.save(new UserProfile());
		}

		if (userService.findByLogin(user.getEmail()) != null) {
			FieldError loginError = new FieldError("user", "email",
					messageSource.getMessage("error.email", null, locale));
			br.addError(loginError);
		}

		if (!user.getEmail().matches("^([a-z]+[a-z0-9]*@[a-z]+.[a-z]{2,6})$")) {
			FieldError loginError = new FieldError("user", "email",
					messageSource.getMessage("error.emailpattern", null, locale));

			br.addError(loginError);
		}

		if (!user.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
			FieldError loginError = new FieldError("user", "password",
					messageSource.getMessage("error.passwordpattern", null, locale));
			br.addError(loginError);
		}

		if (!br.hasErrors()) {
			if (user != null) {
				user.setUserProfile(userProfile);
				user = userService.save(user);
				request.login(user.getEmail(), user.getPassword());
			}
			return "redirect:/home";
		} else {
			model.addAttribute("pageName", "signup");
			return "signup";
		}

	}
}
