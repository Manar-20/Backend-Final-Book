package com.sda.finalProject.controller;



import com.sda.finalProject.entity.Review;
import com.sda.finalProject.service.impl.ReviewImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ReviewController {


    @Autowired
    private ReviewImplement reviewImpl;
    @GetMapping("/Reviews")
    public List<Review> Reviews(){
        return reviewImpl.getAllReviews() ;
    }
    @GetMapping("getReview/byUser/{userId}")
    public List<Review> getReviewsByUser(@PathVariable Long userId) {
        return reviewImpl.getReviewsByUserId(userId);
    }
    @GetMapping("/ReviewByBook/{bookId}")
    public List<Review> getReviewsByBook(@PathVariable Long bookId) {
        return  reviewImpl.getReviewsByBook(bookId);
    }

    @PostMapping("/addReview/{userId}/{bookId}")
    public ResponseEntity<String> addReview(@PathVariable Long userId, @PathVariable Long bookId, @RequestBody Review review) {
        try {
            Review addReviews = reviewImpl.addReview(userId , bookId , review);
            String successMessage = "Review added successfully for Event with ID " + bookId + " by User with ID " + userId;
            return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
        } catch (Exception e) {
            String errorMessage = "Review not added successfully";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @DeleteMapping("/delete-review/{idReview}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReview(@PathVariable String idReview) {
        reviewImpl.deleteReview(idReview);
    }
}
