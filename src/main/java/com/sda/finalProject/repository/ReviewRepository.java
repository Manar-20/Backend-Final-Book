package com.sda.finalProject.repository;

import com.sda.finalProject.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository  extends JpaRepository<Review, String> {
    List<Review> findByUserId(Long userId);






    List<Review> findByBook(Long bookId);
}
