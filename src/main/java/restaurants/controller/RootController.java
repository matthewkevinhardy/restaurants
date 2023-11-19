package restaurants.controller;

import java.io.IOException;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
class RootController {

	@GetMapping(value = "/")
	public ModelAndView redirect(ModelMap model) throws IOException {
		return new ModelAndView("redirect:/swagger-ui/index.html", model);
	}
}
