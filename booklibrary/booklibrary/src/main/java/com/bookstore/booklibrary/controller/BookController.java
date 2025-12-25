package com.bookstore.booklibrary.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.booklibrary.model.Book;
import com.bookstore.booklibrary.service.BookService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class BookController {
    
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    
    // @CrossOrigin(origins = "http://localhost:3000") --> To handle CORS locally
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id){
        Book book = bookService.getBookById(id);

        if(book==null)return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<?> addBook(@RequestBody Book book){
        try{
            bookService.addBook(book);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/books/{id}")
    public ResponseEntity<String> updateBook(@PathVariable int id ,@RequestBody Book updatedBook){
        try{
            bookService.updateBook(id, updatedBook);
            return new ResponseEntity<>("Book Details Updated", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id){
        Book book = bookService.getBookById(id);

        if(book != null){
            bookService.deleteBook(id);
            return new ResponseEntity<>("Deleted",HttpStatus.ACCEPTED);
        }
        else{
            return new ResponseEntity<>("Book Not Found",HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/books/search")
    public ResponseEntity<List<Book>> searchBook(@RequestParam(required = false) String keyword){
        List<Book> filteredBooks = bookService.searchBooks(keyword);

        return new ResponseEntity<>(filteredBooks, HttpStatus.OK);
    }
}
