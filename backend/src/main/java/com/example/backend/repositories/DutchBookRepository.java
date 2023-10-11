package com.example.backend.repositories;

import com.example.backend.models.Book;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class DutchBookRepository implements BookRepository{

    private List<Book> books = Arrays.asList(new Book("De uitvreter", 1019, 1911, "Nescio"), new Book("Max Hevelaar", 892849, 1923, "Mutatuli"));

    @Override
    public List<Book> getAllBooks() {
        return books;
    }

    @Override
    public void addBook(Book book) {
        books.add(book);

    }


}
