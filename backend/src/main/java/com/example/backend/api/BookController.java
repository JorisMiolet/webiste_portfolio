package com.example.backend.api;


import com.example.backend.exceptions.ItemNotFoundException;
import com.example.backend.models.Book;
import com.example.backend.repositories.BookRepository;
import com.example.backend.repositories.EnglishBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<Book> getBooks() {

        return bookRepository.getAllBooks();
    }

    @GetMapping(path = "{id}")
    public Book getBookById(@PathVariable long id){
        for (Book book : bookRepository.getAllBooks()){
            if (book.getId() == id) {
                return book;
            }
        }
        throw new ItemNotFoundException("Book not found");
    }


    @PostMapping("")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        long idToSet =  bookRepository.getAllBooks().size() + 1;
        book.setId(idToSet);
        bookRepository.addBook(book);
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").
                buildAndExpand(idToSet).toUri();
        return ResponseEntity.created(location).body(bookRepository.getAllBooks().get((int) idToSet -1));

    }

}
