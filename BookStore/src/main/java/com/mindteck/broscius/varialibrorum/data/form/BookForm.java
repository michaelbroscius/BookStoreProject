package com.mindteck.broscius.varialibrorum.data.form;

public class BookForm {
	private String title;
	private Long authorId;
	private Long categoryId;
	private Long publisherId;
	private int publicationYear;

	private String description;
	private double price;
	private int numberInStock;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Long publisherId) {
		this.publisherId = publisherId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getNumberInStock() {
		return numberInStock;
	}

	public void setNumberInStock(int numberInStock) {
		this.numberInStock = numberInStock;
	}

	@Override
	public String toString() {
		return "BookForm [title=" + title + ", authorId=" + authorId + ", categoryId=" + categoryId + ", publisherId="
				+ publisherId + ", publicationYear=" + publicationYear + ", description=" + description + ", price="
				+ price + ", numberInStock=" + numberInStock + "]";
	}



}
