package com.mindteck.broscius.varialibrorum.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindteck.broscius.varialibrorum.data.entity.Book;
import com.mindteck.broscius.varialibrorum.data.repository.BookRepository;

@RestController
public class BookRestController {
	@Autowired
	private BookRepository bookRepository;

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	List<Book> findAll(@RequestParam(required = false) Long id) {
		List<Book> books = new ArrayList<>();
		if (null == id) {
			books = this.bookRepository.findAll();
		} else {
			Book book = this.bookRepository.findOne(id);
			if (null != book) {
				books.add(book);
			}
		}
		return books;
	}

}
