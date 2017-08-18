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

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "publisher_id", nullable = true)
	private Publisher publisher;

	private Integer publicationYear;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id", nullable = true)
	private Category category;

	public Book() {
	}

	public Book(String description, float price, int numberInStock, String title, Author author, Publisher publisher,
			Integer publicationYear, Category category) {
		super(title, description, price, numberInStock);
		this.author = author;
		this.publisher = publisher;
		this.publicationYear = publicationYear;
		this.category = category;
	}

	public Book(String title, Author author, Publisher publisher, int publicationYear, Category category) {
		setName(title);
		this.author = author;
		this.publisher = publisher;
		this.publicationYear = publicationYear;
		this.category = category;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((publicationYear == null) ? 0 : publicationYear.hashCode());
		result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (publicationYear == null) {
			if (other.publicationYear != null)
				return false;
		} else if (!publicationYear.equals(other.publicationYear))
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [author=" + author + ", publisher=" + publisher + ", publicationYear=" + publicationYear
				+ ", category=" + category + super.toString() + "]";
	}

}
