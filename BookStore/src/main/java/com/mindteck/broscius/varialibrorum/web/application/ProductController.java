package com.mindteck.broscius.varialibrorum.web.application;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mindteck.broscius.varialibrorum.business.service.BookService;

@Controller
public class ProductController {
	private final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	BookService bookService;

	@RequestMapping(value = { "/index", "/products.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String showIndex(Model model, HttpSession session) {
		logger.debug(
				"Entered showProductForm(Model model, HttpSession session) for @RequestMapping( value = { \"/index\", \"/products.html\" }).");

		if (!ControllerUtilities.isUserAuthenticated(session)) {
			logger.info("User not authenticated. Redirecting to login page.");
			session.invalidate();
			return "redirect:/login";
		}

		// TODO change books to products
		addBooksToModel(model);
		logger.debug("showIndex() returning \"index\".");
		return "index";
	}

	/**
	 * @param model
	 */
	public Model addBooksToModel(Model model) {
		model.addAttribute("books", bookService.getAllBooks());

		return model;
	}

}
