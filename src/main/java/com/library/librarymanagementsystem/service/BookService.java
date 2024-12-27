package com.library.librarymanagementsystem.service;

import com.library.librarymanagementsystem.entity.Book;
import com.library.librarymanagementsystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    // Fetch all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Save a book
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    // Delete a book
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    // Update an existing book
    public Book updateBook(Long id, Book updatedBookDetails) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id " + id));

        existingBook.setTitle(updatedBookDetails.getTitle());
        existingBook.setAuthor(updatedBookDetails.getAuthor());
        existingBook.setIsbn(updatedBookDetails.getIsbn());
        existingBook.setIsIssued(updatedBookDetails.getIsIssued());

        return bookRepository.save(existingBook);
    }
}
