
package com.bookstore.booklibrary.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bookstore.booklibrary.model.Book;
import com.bookstore.booklibrary.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepo;

    @InjectMocks
    private BookService bookService;

    private Book book;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        book = new Book();
        book.setId(1);
        book.setTitle("Clean Code");
        book.setAuthor("Robert C. Martin");
    }

    @Test
    void shouldReturnAllBooks() {
        when(bookRepo.findAll()).thenReturn(List.of(book));

        List<Book> books = bookService.getAllBooks();

        assertEquals(1, books.size());
        assertEquals("Clean Code", books.get(0).getTitle());
        verify(bookRepo, times(1)).findAll();
    }

    @Test
    void shouldReturnBookWhenIdExists() {
        when(bookRepo.findById(1)).thenReturn(Optional.of(book));

        Book result = bookService.getBookById(1);

        assertNotNull(result);
        assertEquals("Clean Code", result.getTitle());
        verify(bookRepo, times(1)).findById(1);
    }

    // For returning null if no book is found
    // @Test
    // void shouldReturnNullWhenBookIdDoesNotExist() {
        
    //     when(bookRepo.findById(99)).thenReturn(Optional.empty());

    //     Book result = bookService.getBookById(99);

    //     assertNull(result);
    //     verify(bookRepo, times(1)).findById(99);
    // }

    @Test
    void shouldThrowExceptionWhenBookNotFound() {
        when(bookRepo.findById(99)).thenReturn(Optional.empty());

        RuntimeException runtimeException= assertThrows(RuntimeException.class, () -> {
            bookService.getBookById(99);
        });

        assertEquals("Book not found", runtimeException.getMessage());
    }

    @Test
    public void shouldSaveandReturnBookSuccessfully(){
        
        // Data Preparation (done in common Before All)

        // Mocking calls
        when(bookRepo.save(book)).thenReturn(book);
        // calling your actual methods
        Book addedBook = bookService.addBook(book);
       
        assertNotNull(addedBook);
        assertEquals("Clean Code", addedBook.getTitle());
        verify(bookRepo, times(1)).save(book);
        assertEquals(book.getId(), addedBook.getId());

    }
   
    @Test 
    public void deleteBookShouldDeleteBookSuccessfully(){
        
        doNothing().when(bookRepo).deleteById(1);
        
        bookService.deleteBook(1);
        verify(bookRepo, times(1)).deleteById(1);
        
    }
    
}