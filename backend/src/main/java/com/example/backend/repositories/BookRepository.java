package com.example.backend.repositories;

import com.example.backend.models.Book;

import java.util.List;

public interface BookRepository {

    List<Book> getAllBooks();

    void addBook(Book book);
}
