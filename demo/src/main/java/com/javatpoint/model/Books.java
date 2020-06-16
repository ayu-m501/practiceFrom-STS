package com.javatpoint.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.javatpoint.exception.ExceptionInfo;

@Entity
@Table(name = "book_s")
public class Books implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "book_id")
	private int bookid;

	@Column(name = "book_name")
	@Size(min = 2, max = 35, message = "bookname must be 2-35 characters long.")
	@NotEmpty(message = "bookname must not be empty")
	private String bookname;

	@Column(name = "book_author")
	@NotEmpty(message = "author must not be empty")
	private String author;

	@Column(name = "book_price")
	private int price;

	@Column(name = "made_in")
	private String madeIn;

	@Column(name = "effective_From_Date")
	private String effectiveFromDate;

	@Column(name = "effective_To_Date")
	private String effectiveToDate;

	@Column(name = "sequence_number")
	private Integer sequenceNumber;

	@Column(name = "createdDate")
	private Date createdDate;

	ArrayList<ExceptionInfo> exceptionInfo = new ArrayList<ExceptionInfo>();

	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getMadeIn() {
		return madeIn;
	}

	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getEffectiveFromDate() {
		return effectiveFromDate;
	}

	public void setEffectiveFromDate(String effectiveFromDate) {
		this.effectiveFromDate = effectiveFromDate;
	}

	public String getEffectiveToDate() {
		return effectiveToDate;
	}

	public void setEffectiveToDate(String effectiveToDate) {
		this.effectiveToDate = effectiveToDate;
	}

	public ArrayList<ExceptionInfo> getExceptionInfo() {
		return exceptionInfo;
	}

	public void setExceptionInfo(ArrayList<ExceptionInfo> exceptionInfo) {
		this.exceptionInfo = exceptionInfo;
	}

	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}