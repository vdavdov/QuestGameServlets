package com.javarush.by.vdavdov.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue
    @Column(name="id", unique = true)
    private int id;
    @Column(name="name", nullable = true, length = 255)
    private String name;
    @Column(name="level", nullable = false)
    private int level;
    @Column(name="createdDate", nullable = false)
    private Date createdDate;

}
