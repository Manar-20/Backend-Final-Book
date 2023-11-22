package com.sda.finalProject.service.interfaces;



import com.sda.finalProject.entity.Rating;

import java.util.List;

public interface RatingSarvice {
    List<Rating> getRatingsByUserId(Long userId);








    Rating addRatingByUserAndBookId(Long userId, Long bookId, Rating rating);

    List<Rating> getAllRatings();


    String deleteRating(String idRate);

    List<Rating> getRatingsByBook(Long bookId);
}
