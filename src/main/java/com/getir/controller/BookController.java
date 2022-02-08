package com.getir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.getir.entity.Book;
import com.getir.exception.BookNotfoundException;
import com.getir.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = "/insertbook", method = {RequestMethod.POST})
	public ResponseEntity<Object> insertBook (@RequestBody Book book) {
		book =  bookService.insertBook(book);
		return new ResponseEntity<>(
				"Book is created successfully with Id = " + book.getId(),
				HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/getbookbyid", method = RequestMethod.GET)
	public ResponseEntity<Object> updateEmployee(@RequestParam(name = "id") Long id) {
		Book bookExist = bookService.findById(id);
		if (bookExist != null)
		{
			return new ResponseEntity<>(bookExist, HttpStatus.OK);
		}
		else
		{
			throw new BookNotfoundException();
		}

	}
	
	@RequestMapping(value = "/updatebook", method = RequestMethod.POST)
	public ResponseEntity<Object> updateEmployee(@RequestParam(name = "id") Long id,
			@RequestBody Book book)
	{
		bookService.updateBook(id, book);
		
		return new ResponseEntity<>("Book is updated successsfully", HttpStatus.OK);

	}
}