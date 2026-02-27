package com.bookstore.booklibrary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.booklibrary.model.Book;
import com.bookstore.booklibrary.repository.BookRepository;

@Service
public class BookService {
    
    private BookRepository bookRepo;
    @Autowired
    public BookService(BookRepository bookRepo){
        this.bookRepo = bookRepo;
    }
    public List<Book> getAllBooks(){
        return bookRepo.findAll();
    }

    public Book getBookById(int id){
        return bookRepo.findById(id)
         .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Book addBook(Book book){
        return bookRepo.save(book);
    }
    public Book updateBook(int id, Book updatedBook) {
        updatedBook.setId(id);
        return bookRepo.save(updatedBook);
        
    }
    public void deleteBook(int id) {

        bookRepo.deleteById(id);
    }

    public List<Book> searchBooks(String keyword){
        if (keyword == null || keyword.trim().isEmpty()) {
            return bookRepo.findAll();
        }
        return bookRepo.searchBooks(keyword);
    }
}
