package com.sda.finalProject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "review")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @Column(name="id_Review",nullable = false, unique = true)
    private String  idReview;

    @PrePersist
    public void generateCustomId() {
        this.idReview = "review" + UUID.randomUUID().toString().substring(1, 6);}
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;


}
