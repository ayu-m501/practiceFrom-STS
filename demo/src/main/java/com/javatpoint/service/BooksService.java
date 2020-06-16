package com.javatpoint.service;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatpoint.exception.BusinessException;
import com.javatpoint.exception.CommonExcetion;
import com.javatpoint.exception.ExceptionCode;
import com.javatpoint.model.Books;
import com.javatpoint.model.OneCountry;
import com.javatpoint.repository.BooksRepository;

//defining the business logic  
@Service
public class BooksService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	BooksRepository booksRepository;

	@Autowired
	OneCountryService oneCountryService;

	public static final String FROMDATELESSTODATE = "effectiveFromDate should be less than effectiveToDate";

//getting all books record by using the method findaAll() of CrudRepository  
	public List<Books> getAllBooks() {
		List<Books> books = new ArrayList<Books>();
		booksRepository.findAll().forEach(books1 -> books.add(books1));
		return books;
	}

//getting a specific record by using the method findById() of CrudRepository  
	public Books getBooksById(int id) {
		return booksRepository.findById(id).get();
	}

//saving a specific record by using the method save() of CrudRepository  
	public void saveOrUpdate(Books books) throws ParseException {

		// check Sequence number
		checkSequenceNumber(books);

		// check duplicate book in database
		checkDuplicateBookName(books);

		// date validation method
		isValidDate(books);

		// validate against oneCountry table
		if (books.getMadeIn() != null && !books.getMadeIn().isEmpty()) {
			boolean countryCodeFlag = isValidCountryCode(books);
			if (countryCodeFlag == false) {
				books.getExceptionInfo().add(
						CommonExcetion.throwParamException(ExceptionCode.EXCEPTIONCODE005, books.getMadeIn(), null));
			} else if (books.getAuthor().length() > 100) {
				books.getExceptionInfo().add(
						CommonExcetion.throwParamException(ExceptionCode.EXCEPTIONCODE002, books.getAuthor(), null));
			} else {
				books.setCreatedDate(new Date());
				books.setExceptionInfo(books.getExceptionInfo());
				booksRepository.save(books);
			}
		}
	}

	public void checkSequenceNumber(Books books) {

		int seqNumberPostman = books.getSequenceNumber();
		if (seqNumberPostman == 0) {
			throw new BusinessException("sequenceNumber should not be started from 0");
		}

		List<Books> bookList = getAllBooks();
		int originalsequenceNumber = 0;
		for (int i = 0; i < bookList.size(); i++) {
			if (bookList.get(i).getSequenceNumber() != null) {
				int sequenceNumber = bookList.get(i).getSequenceNumber();

				if (sequenceNumber > originalsequenceNumber) {
					originalsequenceNumber = sequenceNumber;
				}
			} else {
				throw new BusinessException("sequenceNumber can not be null");
			}
		}
		books.setSequenceNumber(originalsequenceNumber + 1);

	}

	public void checkDuplicateBookName(Books books) {
		List<Books> list = getAllBooks();
		camelCase(books);
		for (int i = 0; i < list.size(); i++) {
			String bookName1 = list.get(i).getBookname();
			String bookName2 = books.getBookname().substring(0, 1).toUpperCase() + books.getBookname().substring(1);
			int str = bookName1.compareTo(bookName2);
			if (str == 0) {
				throw new BusinessException("BookName already stored in database");
			}
		}
	}

	public void isValidDate(Books books) throws ParseException {

		Date effectiveFromD = new SimpleDateFormat("yyyy-mm-dd").parse(books.getEffectiveFromDate());
		Date effectiveT = new SimpleDateFormat("yyyy-mm-dd").parse(books.getEffectiveToDate());

		LocalDate localDate = LocalDate.parse(new SimpleDateFormat("yyyy-mm-dd").format(effectiveFromD));
		int j = localDate.toString().compareTo(LocalDate.now().toString());

		if (effectiveFromD.after(effectiveT)) {
//			books.getExceptionInfo().add(CommonExcetion.throwParamException(ExceptionCode.EXCEPTIONCODE003,
//					books.getEffectiveFromDate(), books.getEffectiveToDate()));
			throw new BusinessException("effectiveFromdate  should be less than effectiveTOdate");
		} else if (j != 0) {
//			books.getExceptionInfo().add(CommonExcetion.throwParamException(ExceptionCode.EXCEPTIONCODE004,
//					books.getEffectiveFromDate(), localDate.now().toString()));
			throw new BusinessException("effectiveFromdate should be equal to currentdate");
		}

	}

	public void camelCase(Books books) {
		String newS = "";
		String newS1 = "";
		String newS2 = "";
		String newS3 = "";
		String bookName = books.getBookname();
		if (bookName != null || !bookName.isEmpty()) {

			String[] splitBN = bookName.split(" ");

			for (String sbn : splitBN) {

				newS = newS + " " + sbn.substring(0, 1).toUpperCase() + sbn.substring(1);
			}

			books.setBookname(newS.trim());
		} else {
			books.getExceptionInfo().add(CommonExcetion.throwNonParamException(ExceptionCode.EXCEPTIONCODE001));
		}

	}

	public boolean isValidCountryCode(Books books) {
		List<OneCountry> countryCodeList = oneCountryService.getAllCountryCode();
		String madeIn = books.getMadeIn();
		boolean flag = false;
		int count = 0;

		for (int i = 0; i < countryCodeList.size(); i++) {

			flag = countryCodeList.get(i).getCountryCodeList().contains(madeIn);

			if (flag == true) {
				count++;

			}
			flag = true;
		}

		if (count == 0) {
			flag = false;
		}

		return flag;
	}

	public void delete(int id) {
		booksRepository.deleteById(id);
	}

//updating a record  
	public void update(Books books, int bookid) {
		booksRepository.save(books);
	}
}