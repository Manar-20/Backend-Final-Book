package com.sda.finalProject.repository;


import com.sda.finalProject.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RatingRepository extends JpaRepository<Rating, String> {
    List<Rating> findByUserId(Long userId);
    List<Rating> findByBook(Long bookId);
}
