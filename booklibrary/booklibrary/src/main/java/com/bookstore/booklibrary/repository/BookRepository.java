package com.bookstore.booklibrary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookstore.booklibrary.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
    // JPQL
    @Query("""
        SELECT b FROM Book b
        WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR LOWER(b.author) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR LOWER(b.isbn) LIKE LOWER(CONCAT('%', :keyword, '%'))
    """)
    List<Book> searchBooks(String keyword);
}
