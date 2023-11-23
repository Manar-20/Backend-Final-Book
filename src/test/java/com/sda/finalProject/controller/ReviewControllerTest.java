package com.sda.finalProject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.finalProject.controller.ReviewController;
import com.sda.finalProject.entity.Review;
import com.sda.finalProject.service.impl.ReviewImplement;
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

public class ReviewControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ReviewImplement reviewImpl;

    @InjectMocks
    private ReviewController reviewController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();
    }

    @Test
    public void testGetAllReviews() throws Exception {
        Review review = new Review();
        review.setIdReview("1");
        review.getUser();
        review.getBook();

        List<Review> reviews = Collections.singletonList(review);

        when(reviewImpl.getAllReviews()).thenReturn(reviews);

        mockMvc.perform(get("/Reviews"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].userId").value(1L))
                .andExpect(jsonPath("$[0].bookId").value(1L));

        verify(reviewImpl, times(1)).getAllReviews();
        verifyNoMoreInteractions(reviewImpl);
    }

    // Similar tests for other methods...

    @Test
    public void testAddReview() throws Exception {
        Review review = new Review();
        review.setIdReview("1");
        review.getUser();
        review.getBook();

        when(reviewImpl.addReview(1L, 1L, review)).thenReturn(review);

        mockMvc.perform(post("/addReview/{userId}/{bookId}", 1L, 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(review)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Review added successfully for Event with ID 1 by User with ID 1"));

        verify(reviewImpl, times(1)).addReview(1L, 1L, review);
        verifyNoMoreInteractions(reviewImpl);
    }

    @Test
    public void testDeleteReview() throws Exception {
        doNothing().when(reviewImpl).deleteReview("1");

        mockMvc.perform(delete("/delete-review/{idReview}", "1"))
                .andExpect(status().isOk());

        verify(reviewImpl, times(1)).deleteReview("1");
        verifyNoMoreInteractions(reviewImpl);
    }
}
