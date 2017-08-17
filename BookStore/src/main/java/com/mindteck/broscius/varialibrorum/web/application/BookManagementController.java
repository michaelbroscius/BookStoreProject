package com.mindteck.broscius.varialibrorum.web.application;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mindteck.broscius.varialibrorum.business.service.BookService;
import com.mindteck.broscius.varialibrorum.data.entity.Book;
import com.mindteck.broscius.varialibrorum.data.form.BookForm;

@Controller
public class BookManagementController {
	@Autowired
	BookService bookService;

	@GetMapping("/bookManagement")
	public String showBookForm(BookForm bookForm, Model model) {
		addBooksAndMapsToModel(model);
		return "bookManagement";
	}

	// TODO implement security
	@PostMapping("/bookManagement")
	public String saveBook(@Valid BookForm bookForm, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "bookManagement";
		}

		Book book = bookService.add(bookForm);
		System.out.println("\n\n*************************************************************************************");
		System.out.println("************************** Added " + book + "*****************************\n\n");

		// TODO add paging and sorting to book management page
		addBooksAndMapsToModel(model);
		model.addAttribute("authorMap", bookService.getAuthorMap());

		return "bookManagement";
	}

	/**
	 * @param model
	 */
	public Model addBooksAndMapsToModel(Model model) {
		model.addAttribute("books", bookService.getAllBooks());
		model.addAttribute("authorMap", bookService.getAuthorMap());
		model.addAttribute("publisherMap", bookService.getPublisherMap());
		model.addAttribute("categoryMap", bookService.getCategoryMap());
		return model;
	}
}
