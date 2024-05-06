package com.javarush.by.vdavdov.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
@ToString
public class User {
    public User(String name, int level, LocalDate createdDate) {
        this.name = name;
        this.level = level;
        this.createdDate = createdDate;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true)
    private int id;
    @Column(name="name", nullable = true, length = 255)
    private String name;
    @Column(name="level", nullable = false)
    private int level;
    @Column(name="created_date", nullable = false)
    private LocalDate createdDate;
}
