package com.getir.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getir.entity.Book;
import com.getir.exception.BookNotfoundException;
import com.getir.repository.BookRepository;
import com.getir.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public Book insertBook(Book book) {
		Book savedBook = bookRepository.save(book);
		
		return savedBook;
	}

	@Override
	public Book findById(long id) {
		Book book = null;
		try {
			book = bookRepository.findById(id).get();
		} catch (Exception e) {
			return null;
		}
		return book;
	}

	@Override
	public boolean existsById(long id) {
		
		return bookRepository.existsById(id);
	}

	@Override
	public void updateBook(long id, Book book) {
		Book  bookExist = findById(id);
		if (bookExist != null)	{
			bookExist.setBookname(book.getBookname());
			bookExist.setPrice(book.getPrice());
			bookExist.setStock(book.getStock());
			
			bookRepository.save(book);
		} else {
			throw new BookNotfoundException();
		}
		
	}
}
