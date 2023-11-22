package com.sda.finalProject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "rating")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    @Id
    @Column(name="id_Rate",nullable = false, unique = true)
    private String  idRate;

    @PrePersist
    public void generateCustomId() {
        this.idRate = "rate" + UUID.randomUUID().toString().substring(1, 6);
    }
    private int value;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

}
