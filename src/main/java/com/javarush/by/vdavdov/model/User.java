package com.javarush.by.vdavdov.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
@ToString
public class User {
    @Id
    @GeneratedValue
    @Column(name="id", unique = true)
    private int id;
    @Column(name="name", nullable = true, length = 255)
    private String name;
    @Column(name="level", nullable = false)
    private int level;
    //todo
//    @Column(name="created_date", nullable = false)
//    private LocalDateTime createdDate;

}
