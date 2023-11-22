package com.sda.finalProject.controller;


import com.sda.finalProject.entity.Rating;
import com.sda.finalProject.service.impl.RatingImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class RatingController {


    @Autowired
    private RatingImplement ratingImpl;


    @GetMapping("/Ratings")
    public List<Rating> ratings(){
        return ratingImpl.getAllRatings() ;
    }

    @GetMapping("/getRating/byUser/{userId}")
    public List<Rating> getRatingsByUser(@PathVariable Long userId) {
        return ratingImpl.getRatingsByUserId(userId);
    }
    @GetMapping("/RatingByBook/{bookId}")
    public List<Rating> getRatingsByBook(@PathVariable Long bookId) {
        return ratingImpl.getRatingsByBook(bookId);

    }

    @PostMapping("/add-rating/{userId}/{bookId}")
    public ResponseEntity<String> addRatingByUserAndBookId(
            @PathVariable Long userId,
            @PathVariable Long bookId,
            @RequestBody Rating rating) {
        try {
            Rating addedRating = ratingImpl.addRatingByUserAndBookId(userId, bookId, rating);
            String successMessage = "Rating added successfully for Book with ID " + bookId + " by User with ID " + userId;

            return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
        } catch (Exception e) {
            String errorMessage = "Failed to add rating for Book with ID " + bookId + " by User with ID " + userId + ": " + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }




    @DeleteMapping("/deleteRating/{idRate}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteRating(@PathVariable String idRate){
        try{
            String message = ratingImpl.deleteRating(idRate);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }catch (Exception e){
            String eerMessage = "Rating not deleted successfully" + e.getMessage();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(eerMessage);
        }
    }
}
