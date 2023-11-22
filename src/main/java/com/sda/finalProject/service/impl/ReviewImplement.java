package com.sda.finalProject.service.impl;

import com.sda.finalProject.entity.Book;
import com.sda.finalProject.entity.Review;
import com.sda.finalProject.entity.User;
import com.sda.finalProject.repository.BookRepsitory;
import com.sda.finalProject.repository.ReviewRepository;
import com.sda.finalProject.repository.UserRepository;
import com.sda.finalProject.service.interfaces.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewImplement implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepo;
    @Autowired
    private BookRepsitory bookRepo;
    @Autowired
    private UserRepository userRepo;
    @Override
    public List<Review> getReviewsByUserId(Long userId) {
        try {
            return reviewRepo.findByUserId(userId);
        } catch (Exception e) {
            throw new RuntimeException("Error while getting reviews by user ID: " + e.getMessage(), e);
        }
    }



    @Override
    public void deleteReview(String idReview) {
        try {
            reviewRepo.deleteById(idReview);
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting review: " + e.getMessage(), e);
        }
    }

    @Override
    public Review addReview(Long userId, Long bookId, Review review) {
        User user = userRepo.findById(userId).orElse(null);
        Book book = bookRepo.findById(bookId).orElse(null);

        // Check if either the user or the book is not found
        if (user == null || book == null) {
            // Handle the case where either the user or the book is not found
            return null;
        }

        // Set the user and book for the rating
        review.setUser(user);
        review.setBook(book);
        review.getIdReview();
        // Save the rating
        return reviewRepo.save(review);
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepo.findAll();
    }

    @Override
    public List<Review> getReviewsByBook(Long bookId) {
        return reviewRepo.findByBook(bookId);
    }
}
