package com.mindteck.broscius.varialibrorum.business.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindteck.broscius.varialibrorum.business.service.BookService;
import com.mindteck.broscius.varialibrorum.data.entity.Author;
import com.mindteck.broscius.varialibrorum.data.entity.Book;
import com.mindteck.broscius.varialibrorum.data.entity.Category;
import com.mindteck.broscius.varialibrorum.data.entity.Publisher;
import com.mindteck.broscius.varialibrorum.data.form.BookForm;
import com.mindteck.broscius.varialibrorum.data.repository.AuthorRepository;
import com.mindteck.broscius.varialibrorum.data.repository.BookRepository;
import com.mindteck.broscius.varialibrorum.data.repository.CategoryRepository;
import com.mindteck.broscius.varialibrorum.data.repository.PublisherRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	PublisherRepository publisherRepository;

	@Transactional
	@Override
	public Book add(BookForm bookForm) {
		//TODO get date from bookForm
		Book book = new Book(bookForm.getTitle(), authorRepository.findOne(bookForm.getAuthorId()), 
				publisherRepository.findOne(bookForm.getPublisherId()), 1975, categoryRepository.findOne(bookForm.getCategoryId()));
		bookRepository.save(book);
		return book;
	}

	@Transactional
	@Override
	public Book add(Book book) {
		return bookRepository.save(book);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Book> getAllBooks() {

		return bookRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Book getBook(Long id) {
		return bookRepository.findOne(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Map<Long, String> getAuthorMap() {
		List<Author> authorList = authorRepository.findAll();

		Map<Long, String> authorMap = authorList.stream().collect(Collectors.toMap(Author::getId, Author::getFullName));

		return authorMap;
	}

	@Transactional(readOnly = true)
	@Override
	public Map<Long, String> getCategoryMap() {
		List<Category> categoryList = categoryRepository.findAll();

		Map<Long, String> categoryMap = categoryList.stream()
				.collect(Collectors.toMap(Category::getId, Category::getName));

		return categoryMap;
	}

	@Transactional(readOnly = true)
	@Override
	public Map<Long, String> getPublisherMap() {
		List<Publisher> publisherList = publisherRepository.findAll();

		Map<Long, String> publisherMap = publisherList.stream()
				.collect(Collectors.toMap(Publisher::getId, Publisher::getName));

		return publisherMap;
	}

}
