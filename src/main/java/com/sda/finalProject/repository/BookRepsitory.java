package com.sda.finalProject.repository;


import com.sda.finalProject.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BookRepsitory extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(String author);

    List<Book> findByTitle(String title);

    Book findByIdBook(Long idBook);
}
