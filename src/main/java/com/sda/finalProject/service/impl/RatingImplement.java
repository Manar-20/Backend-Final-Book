package com.sda.finalProject.service.impl;


import com.sda.finalProject.entity.Book;
import com.sda.finalProject.entity.Rating;
import com.sda.finalProject.entity.User;
import com.sda.finalProject.repository.BookRepsitory;
import com.sda.finalProject.repository.RatingRepository;
import com.sda.finalProject.repository.UserRepository;
import com.sda.finalProject.service.interfaces.RatingSarvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingImplement implements RatingSarvice {
    @Autowired
    private RatingRepository ratingRepo;
    @Autowired
    private BookRepsitory bookRepo;
    @Autowired
    private UserRepository userRepo;

    @Override
    public List<Rating> getRatingsByUserId(Long userId) {
        try {
            return ratingRepo.findByUserId(userId);
        } catch (Exception e) {
            // You might log the exception or handle it based on your specific needs
            throw new RuntimeException("Error while getting ratings by user ID: " + e.getMessage(), e);
        }
    }



    @Override
    public Rating addRatingByUserAndBookId(Long userId, Long bookId, Rating rating) {
        User user = userRepo.findById(userId).orElse(null);
        Book book = bookRepo.findById(bookId).orElse(null);

        // Check if either the user or the book is not found
        if (user == null || book == null) {
            // Handle the case where either the user or the book is not found
            return null;
        }

        // Set the user and book for the rating
        rating.setUser(user);
        rating.setBook(book);
        rating.getIdRate();
        // Save the rating
        return ratingRepo.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepo.findAll();
    }

    @Override
    public String deleteRating(String idRate) {
        if (ratingRepo.existsById(idRate)){
            ratingRepo.deleteById(idRate);
            return "Rating deleted Successfully";
        }else {
            return "Rating not found";
        }
    }


    @Override
    public List<Rating> getRatingsByBook(Long bookId) {
        return ratingRepo.findByBook(bookId);
    }


}

