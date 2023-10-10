package com.example.backend.api;


import com.example.backend.models.Book;
import com.example.backend.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    @Qualifier("EnglishBookRepository")
    private BookRepository bookRepository;

    @GetMapping
    public List<Book> getBooks(){
        return bookRepository.getAllBooks();
    }

    @DeleteMapping
    public String deleteString(){
        return "ik ben verwijdert";
    }

}
