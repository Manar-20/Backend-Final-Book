package com.sda.finalProject.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.finalProject.controller.BookController;
import com.sda.finalProject.entity.Book;
import com.sda.finalProject.service.impl.BookImplement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookImplement bookImpl;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void testCreateNewBook() throws Exception {
        Book book = new Book();
        book.setIdBook(1L);
        book.setTitle("Test Book");

        when(bookImpl.createNewBook(any(Book.class))).thenReturn(book);

        ResultActions testBook = mockMvc.perform((RequestBuilder) post("/add-new-book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.valueOf(new ObjectMapper().writeValueAsString(book))))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.id").value(1L))
                .andExpect((ResultMatcher) jsonPath("$.title").value("Test Book"));

        verify(bookImpl, times(1)).createNewBook(any(Book.class));
        verifyNoMoreInteractions(bookImpl);
    }

    @Test
    public void testGetAllBooks() throws Exception {
        Book book = new Book();
        book.setIdBook(1L);
        book.setTitle("Test Book");

        List<Book> books = Collections.singletonList(book);

        when(bookImpl.getAllBooks()).thenReturn(books);

        ResultActions testBook = mockMvc.perform(get("/all-books"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$[0].id").value(1L))
                .andExpect((ResultMatcher) jsonPath("$[0].title").value("Test Book"));

        verify(bookImpl, times(1)).getAllBooks();
        verifyNoMoreInteractions(bookImpl);
    }

    // Similar tests for other methods...

    @Test
    public void testDeleteBook() throws Exception {
        doNothing().when(bookImpl).deleteBook(1L);

        mockMvc.perform(delete("/delete-book/{idBook}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("Book Deleted Successfully"));

        verify(bookImpl, times(1)).deleteBook(1L);
        verifyNoMoreInteractions(bookImpl);
    }

    @Test
    public void testUpdateBook() throws Exception {
        doNothing().when(bookImpl).updateBook(eq(1L), any(Book.class));

        mockMvc.perform(put("/update/{idBook}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(new Book())))
                .andExpect(status().isOk())
                .andExpect(content().string("Book updated successfully"));

        verify(bookImpl, times(1)).updateBook(eq(1L), any(Book.class));
        verifyNoMoreInteractions(bookImpl);
    }
}