package com.sda.finalProject.service.interfaces;






import com.sda.finalProject.entity.Book;

import java.util.List;

public interface BookService {


    Book createNewBook(Book book);

    List<Book> getAllBooks();

    List<Book> getBooksByAuthor(String author);

    List<Book> getBooksByTitle(String title);

    String deleteBook(Long id);

    String updateBook(Long bookId, Book updatedBook);


    boolean existsById(Long bookId);




    Book getBooksById(Long idBook);
}
