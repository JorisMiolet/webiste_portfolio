package com.example.backend.repositories;

import com.example.backend.models.Book;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class DutchBookRepository implements BookRepository{

    @Override
    public List<Book> getAllBooks() {
        return Arrays.asList(new Book("De uitvreter", 1019), new Book("Max Hevelaar", 892849));
    }
}
