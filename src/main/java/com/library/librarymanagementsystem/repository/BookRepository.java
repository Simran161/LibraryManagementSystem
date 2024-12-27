package com.library.librarymanagementsystem.repository;

import com.library.librarymanagementsystem.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

// Repository Interface for Book Entity
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Custom Query Method to Find a Book by Title
    Optional<Book> findByTitle(String title);
}

