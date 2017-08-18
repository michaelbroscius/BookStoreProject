package com.mindteck.broscius.varialibrorum.web.application;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mindteck.broscius.varialibrorum.business.service.BookService;
import com.mindteck.broscius.varialibrorum.data.form.ProductForm;

@Controller
public class ProductController {
	@Autowired
	BookService bookService;

	@GetMapping("/index")
	public String showBookForm(ProductForm productForm, Model model) {
		System.out.println("\n\n\n*********************************************");
		System.out.println("************ sent to product controller by GET *****");
		System.out.println("*********************************************\\n\\n\\n");
		addBooksToModel(model);
		return "index";
	}

	@PostMapping("/index")
	public String saveBook(ProductForm productForm, Model model) {
		System.out.println("\n\n\n*********************************************");
		System.out.println("************ sent to product controller by POST *****");
		System.out.println("*********************************************\n\n\n");

		addBooksToModel(model);
		model.addAttribute("authorMap", bookService.getAuthorMap());

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
