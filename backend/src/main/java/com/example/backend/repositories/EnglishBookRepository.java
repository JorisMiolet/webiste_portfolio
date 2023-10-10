package com.example.backend.repositories;

import com.example.backend.models.Book;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;


@Repository
@Qualifier("EnglishBookRepository")
public class EnglishBookRepository implements BookRepository{


    @Override
    public List<Book> getAllBooks() {
        return Arrays.asList(new Book("Of mice and man", 1019), new Book("Lord of the flies", 892849));
    }
}
