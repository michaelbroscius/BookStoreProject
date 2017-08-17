package com.mindteck.broscius.varialibrorum.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindteck.broscius.varialibrorum.data.entity.Author;
import com.mindteck.broscius.varialibrorum.data.entity.Book;
import com.mindteck.broscius.varialibrorum.data.entity.Category;
import com.mindteck.broscius.varialibrorum.data.entity.Publisher;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	List<Book> findByAuthor(Author author);

	List<Book> findByPublisher(Publisher publisher);

	List<Book> findByCategory(Category category);

	List<Book> findByTitle(String title);

	List<Book> findByPublicationYear(Integer publicationYear);

	List<Book> findByAuthorLastName(String lastName);

}
