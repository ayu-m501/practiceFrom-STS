package com.javatpoint.model;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "book_details")
public class BookDetail implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "about_book")
	private String aboutBook;
	
	@Column(name = "about_book_author")
	private String aboutBookAuthor;

	public String getAboutBook() {
		return aboutBook;
	}

	public void setAboutBook(String aboutBook) {
		this.aboutBook = aboutBook;
	}

	public String getAboutBookAuthor() {
		return aboutBookAuthor;
	}

	public void setAboutBookAuthor(String aboutBookAuthor) {
		this.aboutBookAuthor = aboutBookAuthor;
	}
	
	
}
