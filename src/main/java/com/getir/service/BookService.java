package com.getir.service;

import com.getir.entity.Book;

public interface BookService {

	public Book insertBook(Book book);

	public Book findById(long id);

	public boolean existsById(long id);

	public void updateBook(long id, Book book);

}
