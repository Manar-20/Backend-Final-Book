package com.sda.finalProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book   {

    @Id
    private Long idBook;

    private String title;
    private String author;
    private String description;
    private String imgUrl;

    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToMany(mappedBy = "book")
    @JsonIgnoreProperties("book")
    private List<Review> reviews;

    @OneToMany(mappedBy = "book")
    @JsonIgnoreProperties("book")
    private List<Rating> ratings;

    public Book(Long idBook, String title, String author, String description, String imgUrl, Category category) {
        this.idBook = idBook;
        this.title = title;
        this.author = author;
        this.description = description;
        this.imgUrl = imgUrl;
        this.category = category;
    }
}
