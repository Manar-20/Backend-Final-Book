package com.sda.finalProject.service.interfaces;



import com.sda.finalProject.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getReviewsByUserId(Long userId);



    void deleteReview(String idReview);

    Review addReview(Long userId, Long bookId, Review review);

    List<Review> getAllReviews();

    List<Review> getReviewsByBook(Long bookId);
}
