package com.sda.finalProject.service.impl;

import com.sda.finalProject.entity.Book;
import com.sda.finalProject.repository.BookRepsitory;
import com.sda.finalProject.service.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookImplement implements BookService {
    @Autowired
    private BookRepsitory bookRepo;


    @Override
    public Book createNewBook(Book book) {

            try {
                return bookRepo.save(book);
            } catch (Exception e) {
                throw new RuntimeException("Error while saving book: " + e.getMessage(), e);
            }

    }

    @Override
    public List<Book> getAllBooks() {
            try {
                return bookRepo.findAll();
            } catch (Exception e) {
                throw new RuntimeException("Error while retrieving all books: " + e.getMessage(), e);
            }
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
            try {
                //  method for finding books by author
                return bookRepo.findByAuthor(author);
            } catch (Exception e) {
                throw new RuntimeException("Error while retrieving books by author: " + e.getMessage(), e);
            }


    }

    @Override
    public List<Book> getBooksByTitle(String title) {
            try {
                // Add repository method for finding books by title
                return bookRepo.findByTitle(title);
            } catch (Exception e) {
                throw new RuntimeException("Error while retrieving books by title: " + e.getMessage(), e);
            }

    }

    @Override
    public String deleteBook(Long idBook) {

    Optional<Book> bookOptional = bookRepo.findById(idBook);
    // If a Book with the specified ID is found, delete them from the database.
        if (bookOptional.isPresent()) {
        bookRepo.deleteById(idBook);
        return "Book is Deleted";
    } else {
        // If no Book with the specified ID is found, return a message that Book is not found.
        return "The BookId is Not Found";
    }    }


    public boolean existsById(Long bookId) {
        return bookRepo.existsById(bookId);
    }

    @Override
    public Book getBooksById(Long idBook) {
        return bookRepo.findByIdBook(idBook);
    }



    public String updateBook(Long idBook, Book updatedBook) {
        // Assuming your Book entity has an 'id' field annotated with @Id
        Optional<Book> optionalBook = bookRepo.findById(idBook);

        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();

            // Update fields with non-null values from updatedBook
            if (updatedBook.getTitle() != null) {
                existingBook.setTitle(updatedBook.getTitle());
            }
            if (updatedBook.getDescription() != null) {
                existingBook.setDescription(updatedBook.getDescription());
            }
            if (updatedBook.getAuthor() != null) {
                existingBook.setAuthor(updatedBook.getAuthor());
            }

            // Save the updated book
            bookRepo.save(existingBook);

            return "Book updated successfully";
        }

        return "Book not found with ID: " + idBook;
    }



}
