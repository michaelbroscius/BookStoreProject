package com.mindteck.broscius.varialibrorum.data.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Book extends Product {

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "author_id")
	private Author author;

	@ManyToOne
	@JoinColumn(name = "publisher_id", nullable = true)
	private Publisher publisher;

	private Integer publicationYear;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = true)
	private Category category;

	public Book() {
	}

	public Book(Author author, Publisher publisher, Integer publicationYear, Category category) {
		this.author = author;
		this.publisher = publisher;
		this.publicationYear = publicationYear;
		this.category = category;
	}

	@Override
	public String toString() {
		return "Book [author=" + author + ", publisher=" + publisher + ", publicationYear=" + publicationYear
				+ ", category=" + category + ", Product=" + super.toString() + "]";
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Integer getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(Integer publicationYear) {
		this.publicationYear = publicationYear;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
