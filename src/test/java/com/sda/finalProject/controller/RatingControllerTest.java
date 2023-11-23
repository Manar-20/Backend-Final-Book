package com.sda.finalProject.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.finalProject.controller.RatingController;
import com.sda.finalProject.entity.Rating;
import com.sda.finalProject.service.impl.RatingImplement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RatingControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RatingImplement ratingImpl;

    @InjectMocks
    private RatingController ratingController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ratingController).build();
    }

    @Test
    public void testGetAllRatings() throws Exception {
        Rating rating = new Rating();
        rating.setId("1");
        rating.setUserId(1L);
        rating.setBookId(1L);

        List<Rating> ratings = Collections.singletonList(rating);

        when(ratingImpl.getAllRatings()).thenReturn(ratings);

        mockMvc.perform(get("/Ratings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].userId").value(1L))
                .andExpect(jsonPath("$[0].bookId").value(1L));

        verify(ratingImpl, times(1)).getAllRatings();
        verifyNoMoreInteractions(ratingImpl);
    }

    // Similar tests for other methods...

    @Test
    public void testAddRatingByUserAndBookId() throws Exception {
        Rating rating = new Rating();
        rating.setIdRate("1");
        rating.getUser();
        rating.getBook();

        when(ratingImpl.addRatingByUserAndBookId(1L, 1L, rating)).thenReturn(rating);

        mockMvc.perform(post("/add-rating/{userId}/{bookId}", 1L, 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(rating)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Rating added successfully for Book with ID 1 by User with ID 1"));

        verify(ratingImpl, times(1)).addRatingByUserAndBookId(1L, 1L, rating);
        verifyNoMoreInteractions(ratingImpl);
    }

    @Test
    public void testDeleteRating() throws Exception {
        when(ratingImpl.deleteRating("1")).thenReturn("Rating deleted successfully");

        mockMvc.perform(delete("/deleteRating/{idRate}", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Rating deleted successfully"));

        verify(ratingImpl, times(1)).deleteRating("1");
        verifyNoMoreInteractions(ratingImpl);
    }
}
