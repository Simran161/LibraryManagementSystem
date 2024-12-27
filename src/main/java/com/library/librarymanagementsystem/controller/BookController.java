package com.library.librarymanagementsystem.controller;

import com.library.librarymanagementsystem.entity.Book;
import com.library.librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;


import java.awt.*;
import java.util.List;

@RestController
@RequestMapping(path ="/api/books",produces = {MediaType.APPLICATION_JSON_VALUE})
public class BookController {
    @Autowired
    private BookService bookService;

    // GET: Retrieve all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books); // Automatically converts List<Book> to JSON
    }

    // POST: Add a new book
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = bookService.saveBook(book);
        return ResponseEntity.ok(savedBook); // Returns the saved book as JSON
    }

    // DELETE: Delete a book by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build(); // Returns HTTP 204 No Content
    }

    // PUT: Update an existing book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book UpdatedBookDetails) {
        Book updatedBook = bookService.updateBook(id, UpdatedBookDetails);
        return ResponseEntity.ok(updatedBook); // Returns the updated book as JSON
    }
}


