package com.mindteck.broscius.varialibrorum.data.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindteck.broscius.varialibrorum.data.entity.Address;
import com.mindteck.broscius.varialibrorum.data.entity.Author;
import com.mindteck.broscius.varialibrorum.data.entity.Book;
import com.mindteck.broscius.varialibrorum.data.entity.Category;
import com.mindteck.broscius.varialibrorum.data.entity.Publisher;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;

	private List<Book> mockBookList;

	@Before
	public void init() {
		mockBookList = new ArrayList<>();
		System.out.println("****************** Creating author ******************");

		System.out.println("****************** Creating book 2 ******************");
		mockBookList.add(new Book("Test Book 2", new Author("Bean", "Jim"),
				new Publisher("Testinghouse Pub. Co.",
						new Address("11 Test Way", "Testville", "PA", "19033", "Test Country"), "215-555-1212"),
				2017, new Category("Test Fiction")));

		mockBookList.add(new Book(
				"Test Book 3", new Author("Testington", "Testy"), new Publisher("BigPub.",
						new Address("11 Test Way", "Testville", "PA", "19033", "Test Country"), "215-555-1212"),
				2017, new Category("Test Fiction")));

		mockBookList.add(new Book("Test Book 4", new Author("Bean", "Jim"),
				new Publisher("Testinghouse Pub. Co.",
						new Address("11 Test Way", "Testville", "PA", "19033", "Test Country"), "215-555-1212"),
				2017, new Category("Test Fiction")));

		mockBookList.add(new Book("For Whom the Test Runs", new Author("Testingtoner", "Testy"),
				new Publisher("Testinghouse Pub. Co.",
						new Address("11 Test Way", "Testville", "PA", "19033", "Test Country"), "215-555-1212"),
				2017, new Category("Test Fiction")));

		mockBookList.add(new Book("Test Book 4", new Author("Bean", "Kim"),
				new Publisher("Testinghouse Pub. Co.",
						new Address("11 Test Way", "Testville", "PA", "19033", "Test Country"), "215-555-1212"),
				2017, new Category("Test Fiction")));

		System.out.println("\n*********************Saving Books******************");
		mockBookList.forEach(book -> {
			System.out.println(book);
			bookRepository.save(book);
		});

	}

	@Test
	public void testBookRepository() {
		Author testAuthor = new Author("Test", "Author");
		System.out.println("****************** Creating book 1 ******************");
		Book mockBook = new Book("Test Book 1", testAuthor,
				new Publisher("Testinghouse Pub. Co.",
						new Address("11 Test Way", "Testville", "PA", "19033", "Test Country"), "215-555-1212"),
				2017, new Category("Test Fiction"));
		bookRepository.save(mockBook);
		mockBookList.add(mockBook);

		List<Book> foundBooks = bookRepository.findByAuthor(testAuthor);

		assertThat(foundBooks).isEqualTo(
				mockBookList.stream().filter(book -> book.getAuthor().equals(testAuthor)).collect(Collectors.toList()));

		System.out.println("\n*************Original Books*************");
		bookRepository.findAll().forEach(System.out::println);
		// TODO fix mappings or ditch book schema.
		// TODO add more tests
	}
}
