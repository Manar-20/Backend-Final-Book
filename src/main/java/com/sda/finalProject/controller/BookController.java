package com.sda.finalProject.controller;


import com.sda.finalProject.entity.Book;
import com.sda.finalProject.service.impl.BookImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class BookController {


    @Autowired
    BookImplement bookImpl;
    @PostMapping("/add-new-book")
    public Book createNewBook(@RequestBody Book book) {
            return bookImpl.createNewBook(book);
    }
    @GetMapping("/all-books")
    public List<Book>getAllBooks() {
        // Call the bookImpl to get a list of all books.
        return bookImpl.getAllBooks();

    }

    @GetMapping("/byAuthor/{author}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String author) {
        // Call the bookImpl to get a list of books by author.
        List<Book> books = bookImpl.getBooksByAuthor(author);
        return ResponseEntity.ok(books);
    }
    @GetMapping("/byBookId/{idBook}")
    public Book getBooksByAuthor(@PathVariable Long idBook) {
        // Call the bookImpl to get a list of books by idBook.
        return bookImpl.getBooksById(idBook);

    }
    @GetMapping("/byTitle/{title}")
    public ResponseEntity<List<Book>> getBooksByTitle(@PathVariable String title) {
        // Call the bookImpl to get a list of books by title.
        List<Book> books = bookImpl.getBooksByTitle(title);
        return ResponseEntity.ok(books);
    }
    @DeleteMapping("/delete-book/{idBook}")
    public ResponseEntity<String> deleteBook(@PathVariable Long idBook) {
        try {
            // Call the BookService to delete a book by BookID.
            bookImpl.deleteBook(idBook);
            String message = "Book Deleted Successfully";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            // If there's an exception, return an error message with the exception details.
            String errorMessage = "Book Deleted Unsuccessfully: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }
    @PutMapping("/update/{idBook}")
    public ResponseEntity<String> updateBook(@PathVariable Long idBook, @RequestBody Book updateBook) {
        try {
            if (!bookImpl.existsById(idBook)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found with ID: " + idBook);
            }
            bookImpl.updateBook(idBook, updateBook);

            return ResponseEntity.ok("Book updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the Book");
        }
    }

}
