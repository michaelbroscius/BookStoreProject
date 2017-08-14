package com.mindteck.broscius.varialibrorum.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mindteck.broscius.varialibrorum.data.entity.Author;
import com.mindteck.broscius.varialibrorum.data.entity.Book;
import com.mindteck.broscius.varialibrorum.data.entity.Category;
import com.mindteck.broscius.varialibrorum.data.entity.Publisher;

public interface BookRepository extends CrudRepository<Book, Long> {
	List<Book> findByAuthor(Author author);
	List<Book> findByPublisher(Publisher publisher);
	List<Book> findByCategory(Category category);
	List<Book> findByName(String name);
	List<Book> findByPublicationYear(Integer publicationYear);

}
