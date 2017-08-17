package com.mindteck.broscius.varialibrorum.business.service;

import java.util.List;
import java.util.Map;

import com.mindteck.broscius.varialibrorum.data.entity.Book;
import com.mindteck.broscius.varialibrorum.data.form.BookForm;

public interface BookService {
	Book add(BookForm bookForm);

	Book add(Book book);

	List<Book> getAllBooks();

	Book getBook(Long id);

	Map<Long, String> getAuthorMap();

	Map<Long, String> getCategoryMap();
	
	Map<Long, String> getPublisherMap();
}