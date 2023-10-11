package com.example.backend.repositories;

import com.example.backend.models.Book;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Repository
@Primary
public class EnglishBookRepository implements BookRepository{

    private List<Book> books = new ArrayList<>(Arrays.asList(new Book("Of mice and man", 1, 1937, "John Steinbeck" ),
            new Book("Lord of the flies", 2, 1954 , "William Golding"),
            new Book ("Midnight's children", 3, 1981, "Rushdie")));


    @Override
    public List<Book> getAllBooks() {
        return books;
    }

    @Override
    public void addBook(Book book) {
        this.books.add(book);

    }


}
