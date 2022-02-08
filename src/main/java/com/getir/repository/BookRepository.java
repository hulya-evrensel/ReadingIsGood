package com.getir.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.getir.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
